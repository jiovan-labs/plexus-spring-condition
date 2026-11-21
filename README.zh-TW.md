[English](README.md) | [簡體中文](README.zh-CN.md) | [繁體中文](README.zh-TW.md)

# Plexus Spring 條件註解

玖漫 plexus-spring-condition 是面向 Spring 的強大條件註解工具包。

## 快速開始

- 匯入相依性

```xml

<dependency>
  <groupId>com.jiovan.plexus</groupId>
  <artifactId>plexus-spring-condition</artifactId>
  <version>LATEST</version>
</dependency>
```

- 通用條件：
  - @ConditionalOnActiveProfile
  - @ConditionalOnAnnotation
  - @ConditionalOnEnvironment
  - @ConditionalOnFile
  - @ConditionalOnHostname
  - @ConditionalOnIpAddress
  - @ConditionalOnLibrary
  - @ConditionalOnLinux
  - @ConditionalOnLocale
  - @ConditionalOnMacAddress
  - @ConditionalOnMacOs
  - @ConditionalOnMultipleCandidates
  - @ConditionalOnPackage
  - @ConditionalOnPort
  - @ConditionalOnPropertyPrefix
  - @ConditionalOnUnixOs
  - @ConditionalOnWindowsOs

- 通用缺失條件：
  - @ConditionalOnMissingEnvironment
  - @ConditionalOnMissingFile
  - @ConditionalOnMissingHostname
  - @ConditionalOnMissingIpAddress
  - @ConditionalOnMissingLibrary
  - @ConditionalOnMissingLocale
  - @ConditionalOnMissingPackage
  - @ConditionalOnMissingProperty
  - @ConditionalOnMissingPropertyPrefix
  - @ConditionalOnMissingResource

- 全部條件的組合：
  - @ConditionalOnAllAnnotations
  - @ConditionalOnAllBeans
  - @ConditionalOnAllBooleanProperties
  - @ConditionalOnAllClasses
  - @ConditionalOnAllEnvironments
  - @ConditionalOnAllExpressions
  - @ConditionalOnAllFiles
  - @ConditionalOnAllLibraries
  - @ConditionalOnAllProperties
  - @ConditionalOnAllSingleCandidates

- 全部缺失條件的組合：
  - @ConditionalOnMissingAllBeans
  - @ConditionalOnMissingAllClasses
  - @ConditionalOnMissingAllEnvironments
  - @ConditionalOnMissingAllFiles
  - @ConditionalOnMissingAllHostnames
  - @ConditionalOnMissingAllIpAddresses
  - @ConditionalOnMissingAllLibraries
  - @ConditionalOnMissingAllProperties

- 任一條件的組合：
  - @ConditionalOnAnyActiveProfiles
  - @ConditionalOnAnyAnnotations
  - @ConditionalOnAnyBeans
  - @ConditionalOnAnyBooleanProperties
  - @ConditionalOnAnyClasses
  - @ConditionalOnAnyEnvironments
  - @ConditionalOnAnyExpressions
  - @ConditionalOnAnyFiles
  - @ConditionalOnAnyHostNames
  - @ConditionalOnAnyIpAddresses
  - @ConditionalOnAnyLibraries
  - @ConditionalOnAnyLocales
  - @ConditionalOnAnyPackages
  - @ConditionalOnAnyPorts
  - @ConditionalOnAnyProperties
  - @ConditionalOnAnyPropertyPrefixes
  - @ConditionalOnAnyResources
  - @ConditionalOnAnySingleCandidates

- 任一缺失條件的組合：
  - @ConditionalOnMissingAnyBeans
  - @ConditionalOnMissingAnyClasses
  - @ConditionalOnMissingAnyEnvironments
  - @ConditionalOnMissingAnyFiles
  - @ConditionalOnMissingAnyLibraries
  - @ConditionalOnMissingAnyPackages
  - @ConditionalOnMissingAnyProperties
  - @ConditionalOnMissingAnyPropertyPrefixes
  - @ConditionalOnMissingAnyResources

## 文件

- GitHub：https://github.com/jiovan-labs/plexus-spring-condition

## 環境要求

- jdk 17+

## 許可證

本專案採用 [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) 許可證。

請參閱 `NOTICE.txt` 檔案瞭解所需的宣告與歸屬資訊。

## 捐贈

喜歡這個工具包嗎？歡迎[向我們捐贈](https://jiovan.com/donation)以支援開發。

## 版權

北京玖漫科技有限公司

## 網站

- 玖漫：https://jiovan.com
