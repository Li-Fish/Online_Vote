package fish.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 *

 * Description: 屏幕显示工具类

 * @author LXM

 * @date 2018年12月12日
 */
public class ViewUtil {
    // 获取屏幕大小
    public static Dimension getFullScreen() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
    //让组件居中显示
    public static void  setScreenCenter(Component comp) {
        Dimension compSize = comp.getSize();
        Dimension screenSize = getFullScreen();
        if(compSize.width > screenSize.width) {
            compSize.width =screenSize.width;
        }
        if(compSize.height > screenSize.height) {
            compSize.height = screenSize.height;
        }
        comp.setLocation((screenSize.width-compSize.width)/2, (screenSize.height-compSize.height)/2);
    }
    //设置指定组件最大化
//
    public static void setFullScreen(Component comp){
        comp.setSize(getFullScreen());
    }
}
