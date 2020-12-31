package run.torva.recycle.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import run.torva.recycle.data.Workout
import javax.inject.Named
import javax.inject.Scope

@Module
@InstallIn(ApplicationComponent::class)
object WorkoutFragmentModule {

    @Provides
    @Named("current_workout")
    fun providesWorkout() = Workout()
}