[![](https://jitpack.io/v/sam38124/JzOnlineData.svg)](https://jitpack.io/#sam38124/JzOnlineData)
[![Platform](https://img.shields.io/badge/平台-%20Android%20-brightgreen.svg)](https://github.com/sam38124)
[![characteristic](https://img.shields.io/badge/特點-%20輕量級%20%7C%20簡單易用%20%20%7C%20穩定%20-brightgreen.svg)](https://github.com/sam38124)
# JzOnlineData
一個能夠及時更新的線上資料儲存庫，使用方式及其簡單，能夠對資料做即時的更新，並且於本地使用~
## 目錄
* [如何導入到專案](#Import)
* [快速使用](#Use)
* [關於我](#About)

<a name="Import"></a>
## 如何導入到項目
> 支持jcenter。 <br/>

### jcenter導入方式
在app專案包的build.gradle中添加
```kotlin
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

在需要用到這個庫的module中的build.gradle中的dependencies中加入
```kotlin
dependencies {
  implementation 'com.github.sam38124: JzOnlineData:1.0'}
```
<a name="Use"></a>
## 快速使用

### 1.建立TXT，輸入資料內容，並且儲存編碼格式為UTF-8，將檔案置於你的伺服器路徑
#### * 第一行為版本號，用來判斷是否要對資料庫進行更新
```kotlin
"version"="1.0";
```
#### * 之後的每一行為你要儲存的資料
```kotlin
"data"="Hello";
```
#### * "必須使用 \ "進行轉義
### 2.開始使用
#### * 於Application添加此行代碼，建議添加於Application
```kotlin
//https://demo.txt更換為你的伺服器檔案路徑
  OnlineData.newInstance.setUP(this,"https://demo.txt",object :
            callback {
            override fun result(a: Boolean) {
                Log.e("加載結果","$a")
            }
        })
```
#### * 取得資料
```kotlin
//使用getOnlineData取得資料內容，如沒有此資料則返回null值
 "data".getOnlineData()
```


<a name="About"></a>
### 關於我
橙的電子android and ios developer

*line:sam38124

*gmail:sam38124@gmail.com
