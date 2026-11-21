[English](README.md) | [简体中文](README.zh-CN.md) | [繁體中文](README.zh-TW.md)

# Plexus Spring 条件注解

玖漫 plexus-spring-condition 是面向 Spring 的强大条件注解工具包。

## 快速开始

- 导入依赖

```xml

<dependency>
  <groupId>com.jiovan.plexus</groupId>
  <artifactId>plexus-spring-condition</artifactId>
  <version>LATEST</version>
</dependency>
```

- 通用条件：
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

- 通用缺失条件：
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

- 全部条件的组合：
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

- 全部缺失条件的组合：
  - @ConditionalOnMissingAllBeans
  - @ConditionalOnMissingAllClasses
  - @ConditionalOnMissingAllEnvironments
  - @ConditionalOnMissingAllFiles
  - @ConditionalOnMissingAllHostnames
  - @ConditionalOnMissingAllIpAddresses
  - @ConditionalOnMissingAllLibraries
  - @ConditionalOnMissingAllProperties

- 任一条件的组合：
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

- 任一缺失条件的组合：
  - @ConditionalOnMissingAnyBeans
  - @ConditionalOnMissingAnyClasses
  - @ConditionalOnMissingAnyEnvironments
  - @ConditionalOnMissingAnyFiles
  - @ConditionalOnMissingAnyLibraries
  - @ConditionalOnMissingAnyPackages
  - @ConditionalOnMissingAnyProperties
  - @ConditionalOnMissingAnyPropertyPrefixes
  - @ConditionalOnMissingAnyResources

## 文档

- GitHub：https://github.com/jiovan-labs/plexus-spring-condition

## 环境要求

- jdk 17+

## 许可证

本项目采用 [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) 许可证。

请参阅 `NOTICE.txt` 文件了解所需的声明与归属信息。

## 捐赠

喜欢这个工具包吗？欢迎[向我们捐赠](https://jiovan.com/donation)以支持开发。

## 版权

北京玖漫科技有限公司

## 网站

- 玖漫：https://jiovan.com
