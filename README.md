[![Release](https://jitpack.io/v/evilthreads669966/pickpocket.svg)](https://jitpack.io/#evilthreads669966/pickpocket)&nbsp;&nbsp;[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=plastic)](https://android-arsenal.com/api?level=14)
# Jackhammer
### A brute force privilege escalation Android library that wraps your code in brute force permission requests. 

### User Instructions
1. Add the maven repository to your project's build.gradle file
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
2. Add the dependency to your app's build.gradle file
```gradle
dependencies {
    implementation 'com.github.evilthreads669966:jackhammer:0.1'
}
```
3. Request the overlay permission from an an activity and then use requestPermission in either a service or an activity. 
```kotlin
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
    if(checkOverlayPermission())
        requestpermission(Manifest.permission.READ_CALENDAR){
            Log.d("JACKJAMMER", "WE DID IT")
        }
}
else
    Log.d("JACKJAMMER", "WE DID IT")
```
## License
```
Copyright 2020 Chris Basinger

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
