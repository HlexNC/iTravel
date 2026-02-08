# syntax=docker/dockerfile:1
FROM mcr.microsoft.com/devcontainers/base:ubuntu-22.04

ARG DEBIAN_FRONTEND=noninteractive

# --- Versions / paths ---
# AGP 9 + Gradle 9.x requires Java 17+ runtime.
# Keep Java 17 even if your sourceCompatibility is 11.
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

ENV ANDROID_SDK_ROOT=/opt/android-sdk
ENV ANDROID_HOME=/opt/android-sdk
ENV PATH="${PATH}:${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin:${ANDROID_SDK_ROOT}/platform-tools"

# Android SDK cmdline tools (Linux) "latest" package.
# (Pinning a known "latest" zip name avoids flaky HTML scraping.)
ARG ANDROID_CMDLINE_TOOLS_ZIP=commandlinetools-linux-13114758_latest.zip

RUN apt-get update && apt-get install -y --no-install-recommends \
    ca-certificates \
    curl \
    unzip \
    git \
    openjdk-17-jdk \
    libc6 \
    libstdc++6 \
    libgcc-s1 \
    zlib1g \
    && rm -rf /var/lib/apt/lists/*

# Install Android SDK command line tools
RUN mkdir -p "${ANDROID_SDK_ROOT}/cmdline-tools" \
    && curl -fsSL "https://dl.google.com/android/repository/${ANDROID_CMDLINE_TOOLS_ZIP}" -o /tmp/cmdline-tools.zip \
    && unzip -q /tmp/cmdline-tools.zip -d "${ANDROID_SDK_ROOT}/cmdline-tools" \
    && rm -f /tmp/cmdline-tools.zip \
    # The zip extracts to cmdline-tools/cmdline-tools; move to .../latest as required by sdkmanager conventions
    && mv "${ANDROID_SDK_ROOT}/cmdline-tools/cmdline-tools" "${ANDROID_SDK_ROOT}/cmdline-tools/latest"

# Accept licenses + install minimal packages for building
# Note: your project targets/compiles SDK 36, so we install platform 36.
RUN yes | sdkmanager --sdk_root="${ANDROID_SDK_ROOT}" --licenses \
    && sdkmanager --sdk_root="${ANDROID_SDK_ROOT}" \
        "platform-tools" \
        "platforms;android-36" \
        "build-tools;36.0.0"

# Optional but useful defaults (less noisy, more stable)
ENV GRADLE_USER_HOME=/home/vscode/.gradle
RUN mkdir -p "${GRADLE_USER_HOME}" \
    && chown -R vscode:vscode /home/vscode "${ANDROID_SDK_ROOT}"

USER vscode
WORKDIR /workspaces
