# 🐍 Android 贪吃蛇游戏

一个基于HTML5 Canvas的Android贪吃蛇游戏，具有现代化的UI设计和优化的触摸控制系统。

## ✨ 特性

- 🎮 **完整游戏逻辑** - 经典的贪吃蛇游戏玩法
- 🎯 **智能方向控制** - 允许反向移动，忽略相同方向
- 📱 **触摸优化** - 支持滑动和按钮双重控制
- 🎨 **响应式设计** - 适配不同屏幕尺寸
- 🔧 **实时状态显示** - 当前方向按钮高亮
- 📊 **分数系统** - 得分记录和最高分保存

## 🎮 游戏截图

![游戏界面](screenshots/gameplay.png)

## 🚀 快速开始

### 环境要求

- Android Studio Arctic Fox 或更高版本
- Android SDK 33+
- JDK 17
- Gradle 8.0+

### 安装步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/yourusername/AndroidSnakeGame.git
   cd AndroidSnakeGame
   ```

2. **构建APK**
   ```bash
   ./gradlew clean assembleDebug
   ```

3. **安装到设备**
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

4. **启动游戏**
   ```bash
   adb shell am start -n com.example.snakegame/.MainActivity
   ```

## 🎯 游戏控制

### 方向控制规则

| 当前方向 | 上键 | 下键 | 左键 | 右键 |
|---------|------|------|------|------|
| **向上** | ❌ 不变 | ✅ 切换 | ✅ 切换 | ✅ 切换 |
| **向下** | ✅ 切换 | ❌ 不变 | ✅ 切换 | ✅ 切换 |
| **向左** | ✅ 切换 | ✅ 切换 | ❌ 不变 | ✅ 切换 |
| **向右** | ✅ 切换 | ✅ 切换 | ✅ 切换 | ❌ 不变 |

### 控制方式

- **触摸滑动** - 在画布上滑动控制方向
- **方向按钮** - 点击方向按钮控制
- **键盘控制** - 使用方向键控制（如果连接键盘）

## 🏗️ 项目结构

```
AndroidSnakeGame/
├── app/
│   ├── src/main/
│   │   ├── assets/
│   │   │   └── android_snake_game3.html    # 游戏主文件
│   │   ├── java/com/example/snakegame/
│   │   │   └── MainActivity.java           # Android主活动
│   │   └── res/                            # 资源文件
│   └── build.gradle.kts                    # 应用级构建配置
├── build.gradle.kts                        # 项目级构建配置
├── gradle.properties                       # Gradle配置
├── settings.gradle.kts                     # 项目设置
├── VERSION.md                              # 版本说明
└── README.md                               # 项目说明
```

## 🔧 技术栈

- **前端**: HTML5 Canvas, JavaScript ES6+, CSS3
- **Android**: Java, WebView, Gradle
- **构建工具**: Android Gradle Plugin 8.0.2
- **目标平台**: Android 5.0+ (API 21+)

## 📱 兼容性

- **最低版本**: Android 5.0 (API 21)
- **目标版本**: Android 13 (API 33)
- **推荐版本**: Android 8.0+ (API 26+)

## 🎨 自定义

### 修改游戏速度
在HTML文件中调整 `gameSpeed` 变量：
```javascript
this.gameSpeed = 150; // 数值越小越快
```

### 修改网格大小
调整 `gridSize` 变量：
```javascript
this.gridSize = 20; // 像素大小
```

### 修改颜色主题
在CSS中自定义颜色变量：
```css
:root {
    --primary-color: #FFD700;
    --secondary-color: #32CD32;
    --background-color: #000;
}
```

## 🐛 问题排查

### 常见问题

1. **构建失败**
   - 检查JDK版本是否为17
   - 确认Android SDK版本
   - 清理项目：`./gradlew clean`

2. **触摸不响应**
   - 检查设备触摸权限
   - 查看控制台日志
   - 确认WebView设置

3. **游戏卡顿**
   - 降低游戏速度
   - 检查设备性能
   - 关闭其他应用

### 调试模式

启用详细日志：
```javascript
console.log('方向设置:', direction);
console.log('触摸事件:', event);
```

## 📝 更新日志

查看 [VERSION.md](VERSION.md) 了解详细的版本历史。

## 🤝 贡献

欢迎提交Issue和Pull Request！

### 贡献指南

1. Fork 项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 🙏 致谢

- HTML5 Canvas API
- Android WebView
- 开源社区

## 📞 联系方式

- 项目链接: [https://github.com/yourusername/AndroidSnakeGame](https://github.com/yourusername/AndroidSnakeGame)
- 问题反馈: [Issues](https://github.com/yourusername/AndroidSnakeGame/issues)

---

⭐ 如果这个项目对你有帮助，请给它一个星标！