# Ant's ToDo's application

## Description
This application for "to-do" lists managing (create, modify, delete).


## Architecture
This is a single-activity application based on [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architectural pattern.


## Features
1. I'm trying to use here the [coroutines](https://github.com/Kotlin/kotlinx.coroutines/blob/master/ui/coroutines-guide-ui.md) instead Rx;
2. [Kodein](https://github.com/Kodein-Framework/Kodein-DI) instead Dagger as a DI framework;
3. All data stores in SQL database and managed via [Room library](https://developer.android.com/topic/libraries/architecture/room);
4. [DataBinding](https://developer.android.com/topic/libraries/data-binding) for bind data and view;
5. [MotionLayout](https://developer.android.com/reference/android/support/constraint/motion/MotionLayout) as a part of UI.

## Screenshots

![](//vk.com/photo1666795_456239672 "Picture 1 - Screen with list's tytles") { width: 200px; height: 280px }
![](//vk.com/photo1666795_456239673 "Picture 2") { width: 200px; height: 280px }
![](//vk.com/photo1666795_456239675 "Picture 3 - All items in selected list") { width: 200px; height: 280px }
![](//vk.com/photo1666795_456239676 "Picture 4 - Swipe for deleting") { width: 200px; height: 280px }
