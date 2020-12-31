# reCycle

[![Android CI Actions Status](https://github.com/torvarun/bike-computer/workflows/Android%20CI/badge.svg)](https://github.com/torvarun/bike-computer/workflows/Android%20CI/badge.svg)

My humble attempt at building my own bike computer to make this injury recovery process more bearable.

This repo contains the android app. Hardware will be up at another repo soon.

## Overview

The microcontroller serves a websocket with JSON data fpr the app connects to. To minimize hardware complexity, only raw data is served (currently just the rear-wheel RPM).

It is the client's responsibility to start a workout session, keep track of time, calculate distance, speed etc.

## Dependencies

- The usual [Android stuff](./app/build.gradle)
    - Dagger Hilt for DI
    - Tinder's [Scarlet](https://github.com/Tinder/Scarlet) for websocket connections
    - Coroutines and the likes

## TODO
[ ] Bluetooth interface

[ ] How to docs

[ ] Variable wheel-size calculations

[ ] Data store for past workouts (Firebase?)

[ ] App theme + logo

[ ] Workout data graphs
