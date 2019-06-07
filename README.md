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
5. [MotionLayout](https://developer.android.com/reference/android/support/constraint/motion/MotionLayout) as a part of UI;
6. Using [Cicerone](https://github.com/terrakok/Cicerone) for navigation;
7. CI for project is managed on [Circle CI](https://circleci.com/).

## Screenshots

Picture 1|Picture 2|Picture 3|Picture 4|Picture 5|
--- | ---- |------|------|------|
<img src="https://pp.userapi.com/c853420/v853420993/5fd5e/uVXEWMa71l4.jpg" width="320" height="280" />|<img src="https://pp.userapi.com/c849432/v849432993/1a74be/ewdqQAP3rsA.jpg" width="320" height="280" />|<img src="https://pp.userapi.com/c848736/v848736993/1b9178/KMHjJ_wQEoU.jpg" width="320" height="280" />|<img src="https://pp.userapi.com/c851336/v851336993/13ef2c/d4HWAjIpaCg.jpg" width="320" height="280" />|<img src="https://pp.userapi.com/c855228/v855228993/5d441/CZRXElCUCsk.jpg" width="320" height="280" />|


## Authors
Pavel Antoshkin ([Papashkin](https://github.com/Papashkin)) - _full realization_


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
