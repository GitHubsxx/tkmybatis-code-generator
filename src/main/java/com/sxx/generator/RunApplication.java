package com.sxx.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @description:
 */
@SpringBootApplication
public class RunApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
        String logo = "//\n" +
                "//                            _ooOoo_\n" +
                "//                           o8888888o\n" +
                "//                           88\" . \"88\n" +
                "//                           (| -_- |)\n" +
                "//                           O\\  =  /O\n" +
                "//                        ____/`---'\\____\n" +
                "//                      .'  \\\\|     |//  `.\n" +
                "//                     /  \\\\|||  :  |||//  \\\n" +
                "//                    /  _||||| -:- |||||-  \\\n" +
                "//                    |   | \\\\\\  -  /// |   |\n" +
                "//                    | \\_|  ''\\---/''  |   |\n" +
                "//                    \\  .-\\__  `-`  ___/-. /\n" +
                "//                  ___`. .'  /--.--\\  `. . __\n" +
                "//               .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n" +
                "//              | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                "//              \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n" +
                "//         ======`-.____`-.___\\_____/___.-`____.-'======\n" +
                "//                            `=---='\n" +
                "//        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
                "//                      佛祖保佑       永无BUG\n" +
                "//\n" +
                "//                       .::::.\n" +
                "//                     .::::::::.\n" +
                "//                    :::::::::::\n" +
                "//                 ..:::::::::::'\n" +
                "//              '::::::::::::'\n" +
                "//                .::::::::::\n" +
                "//           '::::::::::::::..\n" +
                "//                ..::::::::::::.\n" +
                "//              ``::::::::::::::::\n" +
                "//               ::::``:::::::::'        .:::.\n" +
                "//              ::::'   ':::::'       .::::::::.\n" +
                "//            .::::'      ::::     .:::::::'::::.\n" +
                "//           .:::'       :::::  .:::::::::' ':::::.\n" +
                "//          .::'        :::::.:::::::::'      ':::::.\n" +
                "//         .::'         ::::::::::::::'         ``::::.\n" +
                "//     ...:::           ::::::::::::'              ``::.\n" +
                "//    ```` ':.          ':::::::::'                  ::::..\n" +
                "//                       '.:::::'                    ':'````..\n";
        System.out.println(logo);
        System.out.println("代码生成器启动成功...");
    }

}
