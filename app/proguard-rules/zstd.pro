# Related: https://github.com/lysine-dev/okhttp/issues/9591

-keepclasseswithmembers,includedescriptorclasses class com.squareup.zstd.** {
    native <methods>;
}
-keepclassmembers class com.squareup.zstd.** {
    <fields>;
}
-keepnames class com.squareup.zstd.**
