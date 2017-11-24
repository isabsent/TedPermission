# TedPermission - Fork
Easy check permission library for Android Marshmallow.

Used in **TedPermission** Support library conflicted with my project code and was stripped out. Min SDK level was set up to 14. You can make it lower by choosing other theme in `TedPermission/tedpermission/src/main/AndroidManifest.xml` (see [here](https://stackoverflow.com/questions/9832114/how-to-use-device-default-theme-for-app))
Generating an apk from this project you will create `tedpermission-release.aar` library inside of `...\tedpermission\build\outputs\aar` folder which can be used in your Android project if placed in the `libs` folder by means of 

    repositories {
        flatDir {
            dirs 'libs' 
        }
    }
and

    dependencies {
        compile fileTree(include: ['*.jar'], dir: 'libs')
        compile(name: 'tedpermission-2.1.0', ext: 'aar')
    }

in your `app` level build.gradle.
