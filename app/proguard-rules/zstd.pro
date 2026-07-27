# Related:
# https://github.com/square/zstd-kmp/issues/108
# https://github.com/lysine-dev/okhttp/issues/9591

-keep class com.squareup.zstd.ZstdCompressor {
    int inputBytesProcessed;
    int outputBytesProcessed;
}

-keep class com.squareup.zstd.ZstdDecompressor {
    int inputBytesProcessed;
    int outputBytesProcessed;
}
