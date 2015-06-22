WE Vision android SDK - WideEyes We Vision SDK for Android
==============

A library to integrate We Vision search capabilities in your Android app.
If you want to access our web service API directly you can, see the docs [here](http://docs.wide-eyes.it).


Table of Contents
=================
**Getting Started**

1. [Setup](#setup)
1. [Quick Start](#quick-start)
1. [Contributing](#contributing)
1. [License](#license)


Setup
-------------
To setup your project, follow these steps:

1. Download this repository
2. Copy sdk.aar file to your lib folder
3. Open your gradle file and add
  ```Groovy
  repositories {
    // ...
    flatDir {
      dirs 'libs'
    }
  }
  ```

  ```Groovy
  dependencies {
    // ...
    compile 'it.wideeyes.sdk:sdk-release:1.0@aar'
  }
  ```

#### Source Code

```sh
  git clone https://github.com/WideEyesTech/we-vision-android-sdk
```


Quick Start
-------------


##### Instantiate WE Vision Android SDK

In the main Activity insert the next code with your APIKEY

```Java
WE.initialize(getApplicationContext(), "YOUR_APIKEY_HERE");
```

##### WECallable interface

```Java
public interface WECallable {

    void success(WEResult result);
    void error(WEError error);
}
```

##### Get products by Id

```Java
WEVision.searchById(WEGender gender, String category, String subCategory, String productId, final WECallable callable);
```

```Java
WEVision.searchById(WEGender gender, String category, String subCategory, String productId, int minNumResults, int maxNumResults, WECallable callable);
```


##### Get cross search products

```Java
WEVision.crossSearchById(WEGender gender, String category, String subCategory, String productId, final WECallable callable)
```

##### Process an image after drawing the contourn around it

WEImageData class contains a Bitmap(the image) and Point's array(the contour).

> Is your responsability to transform the coordinates of the contour to image coordinates.

```Java
WEImageDataStandardized imageDataStandardized = WEComputerVision.standardize(WEImageData imageData, int margin);
```

##### Get search produts by image using a pre processed image

> Is your responsability to transform the coordinates of the contour to image coordinates.

```Java
WEVision.searchByImage(WEGender gender, String category, String subCategory, WEImageDataStandardized imageData, final WECallable callable);
```

##### Get search products by image using a non-processed image

> Is your responsability to transform the coordinates of the contour to image coordinates.

```Java
WEVision.searchByImage(WEGender gender, String category, String subCategory, WEImageData imageData, final WECallable callable);
```

```Java
WEVision.searchByImage(WEGender gender, String category, String subCategory, WEImageData imageData, int margin, final WECallable callable);
```

Contributing
-----------------

Want to contribute? Check out the [contributing guide](CONTRIBUTING.md)!

License
----------------

Copyright 2015 Wide Eyes Technologies S.L. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.