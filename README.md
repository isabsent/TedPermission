# TedPermission - Fork
Easy check permission library for Android Marshmallow.

Support library was stripped and min SDK level is 14.
Generating signed apk from this project you will create `tedpermission-release.aar` library inside of `build` folder which can be used in your Android project if placed in the `libs` folder by means of 

    repositories {
        flatDir {
            dirs 'libs' //this way we can find the .aar file in libs folder
        }
    }
and

    dependencies {
        compile fileTree(include: ['*.jar'], dir: 'libs')//https://stackoverflow.com/a/37342889
        compile(name: 'tedpermission-2.1.0', ext: 'aar')
    }

in your `app` level build.gradle.
