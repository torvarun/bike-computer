# Notes

A collection of notes for myself as I'm going through the development process. Maybe these will turn into blog posts, but it's most likely they just live here.

## Coroutines

## Flows

- Need to follow a pattern similar to `LiveData` to achieve the observable pattern from RxJava

  ```kotlin
  private val _myValue : MutableStateFlow<Type> = MutableStateFlow(defaultValue)
  public val myValue : StateFlow<Type> = _myValue
  // updating _myValue.value is reflected in myValue
  ```
  - Would love to abstract this so that each class does not need this duplicate setup
  
- Testing Flows is a little buggy. The issues are outline in [this GitHub issue](https://github.com/Kotlin/kotlinx.coroutines/issues/1204)

  - (TLDR: running tests results in `java.lang.IllegalStateException: This job has not completed yet`)

  - `runBlockingTest` is the desired fix, but there is a bug in it (see the above linked issue)

  - Workaround for now is to do one of the following

    1. Use `LiveData` with in tests

       ```kotlin
       @get:Rule
       val instantExecutorRule  = InstantTaskExecutorRule()
       ```

    2. Use non-observables for now and then have an interface layer (maybe the Repository layer) which emits these changed values as a `Flow`/`LiveData`.

       - Should be able to collapse this layer once the bug is fixed
