# 词典数据爬取
使用了`jsoup`来爬取在线的电子词典的数据，可以忽略引入了springboot框架，属于是职业病了，有事没事就把spring搞进来了，其实核心代码没几行。

爬取的数据直接放到页面当做静态资源了，没放到这个repo，而是在`words-netlify`这个repo的public目录，如果需要的话，可以自己转到那个项目去下载资源。

虽然有道提供了付费api，且优惠券足够拉取2w个单词，但是api返回的结果如下，是没有词组和例句的，所以选择了自己到网页上进行爬。

顺带发现的一个有意思的事情是有道的体验页面调用的接口不需要传`secret`也能调用，`https://aidemo.youdao.com/trans?q={单词}`
```json
{
    "returnPhrase":[
        "obvious"
    ],
    "query":"obvious",
    "errorCode":"0",
    "l":"en2zh-CHS",
    "tSpeakUrl":"https://openapi.youdao.com/ttsapi?q=%E6%98%8E%E6%98%BE%E7%9A%84&langType=zh-CHS&sign=CD12602AF71F5797FAE89BB49CC2BD9C&salt=1651249822775&voice=4&format=mp3&appKey=2423360539ba5632&ttsVoiceStrict=false",
    "web":[
        {
            "value":[
                "明显的",
                "显而易见的",
                "明摆着",
                "有目共睹"
            ],
            "key":"Obvious"
        },
        {
            "value":[
                "路人皆知"
            ],
            "key":"Is obvious"
        },
        {
            "value":[
                "效果明显",
                "明显的效应"
            ],
            "key":"Obvious effect"
        }
    ],
    "requestId":"496b225f-a963-436e-89b4-65c0241ed2eb",
    "translation":[
        "明显的"
    ],
    "dict":{
        "url":"yddict://m.youdao.com/dict?le=eng&q=obvious"
    },
    "webdict":{
        "url":"http://mobile.youdao.com/dict?le=eng&q=obvious"
    },
    "basic":{
        "exam_type":[
            "高中",
            "CET4",
            "CET6",
            "考研",
            "IELTS",
            "GRE",
            "商务英语"
        ],
        "us-phonetic":"ˈɑːbviəs",
        "phonetic":"ˈɒbviəs",
        "uk-phonetic":"ˈɒbviəs",
        "uk-speech":"https://openapi.youdao.com/ttsapi?q=obvious&langType=en&sign=D56475319250AC087A2F7A73CAE79ABC&salt=1651249822775&voice=5&format=mp3&appKey=2423360539ba5632&ttsVoiceStrict=false",
        "explains":[
            "adj. 明显的，显然的；明确表示的，毫不掩饰的；平淡无奇的，缺乏想象力的；合情合理的，当然的"
        ],
        "us-speech":"https://openapi.youdao.com/ttsapi?q=obvious&langType=en&sign=D56475319250AC087A2F7A73CAE79ABC&salt=1651249822775&voice=6&format=mp3&appKey=2423360539ba5632&ttsVoiceStrict=false"
    },
    "isWord":true,
    "speakUrl":"https://openapi.youdao.com/ttsapi?q=obvious&langType=en&sign=D56475319250AC087A2F7A73CAE79ABC&salt=1651249822775&voice=4&format=mp3&appKey=2423360539ba5632&ttsVoiceStrict=false"
}
```

在原作者的代码上添加了获取英式音标和英式发音的代码



#### 免责声明

仅供学习参考,切勿用于商业行为,损害他人利益与本人无关