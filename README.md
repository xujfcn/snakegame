# Android 贪吃蛇游戏

这是一个基于WebView的Android贪吃蛇游戏，使用HTML5 Canvas和JavaScript实现游戏逻辑，通过Android WebView进行展示。

## 项目特性

- 🐍 经典的贪吃蛇游戏玩法
- 📱 专为Android设备优化的触摸控制
- 🎮 支持触摸手势和虚拟按键控制
- 🏆 分数记录和最高分保存
- 🎨 现代化的UI设计
- 📊 实时分数和蛇长度显示
- ⏸️ 暂停/继续功能
- 🔄 游戏重启功能

## 项目结构

```
AndroidSnakeGame/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/snakegame/
│   │   │   └── MainActivity.java          # 主Activity，负责WebView配置
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml      # 主界面布局
│   │   │   ├── values/
│   │   │   │   ├── colors.xml             # 颜色资源
│   │   │   │   ├── strings.xml            # 字符串资源
│   │   │   │   └── themes.xml             # 主题样式
│   │   │   └── drawable/                  # 图标资源
│   │   └── assets/
│   │       └── android_snake_game3.html   # 游戏HTML文件
│   └── build.gradle.kts                   # 应用级构建配置
├── build.gradle.kts                       # 项目级构建配置
├── settings.gradle.kts                    # 项目设置
└── gradle/                                # Gradle包装器
```

## 技术架构

- **前端**: HTML5 + CSS3 + JavaScript (ES6+)
- **游戏引擎**: 原生JavaScript游戏循环
- **图形渲染**: HTML5 Canvas 2D
- **Android集成**: WebView + 原生Java代码
- **触摸控制**: 触摸事件 + 手势识别
- **数据存储**: LocalStorage (Web) + SharedPreferences (Android)

## 运行要求

- Android Studio Hedgehog | 2023.1.1 或更高版本
- Android SDK 24 (API Level 24) 或更高
- Android Gradle Plugin 8.2.2
- Gradle 8.2 或更高版本

## 安装和运行

### 1. 克隆项目
```bash
git clone <repository-url>
cd AndroidSnakeGame
```

### 2. 在Android Studio中打开
- 启动Android Studio
- 选择 "Open an existing Android Studio project"
- 导航到项目目录并选择

### 3. 同步项目
- 等待Gradle同步完成
- 如果出现依赖问题，点击 "Sync Now"

### 4. 运行应用
- 连接Android设备或启动模拟器
- 点击 "Run" 按钮 (绿色三角形)
- 选择目标设备并运行

## 游戏控制

### 触摸控制
- **滑动**: 在屏幕上滑动来控制蛇的方向
- **点击**: 点击按钮进行游戏操作

### 虚拟按键
- **开始游戏**: 开始新游戏
- **暂停/继续**: 暂停或继续游戏
- **重新开始**: 重置游戏状态

## 游戏规则

1. 控制蛇移动收集食物
2. 每吃一个食物，蛇身增长，分数增加
3. 避免撞到墙壁或自己的身体
4. 游戏结束时会显示最终分数和蛇的长度
5. 支持最高分记录

## 自定义配置

### 修改游戏参数
在 `android_snake_game3.html` 文件中可以调整：
- 游戏速度 (`gameSpeed`)
- 网格大小 (`tileCountX`, `tileCountY`)
- 颜色主题
- 音效设置

### 修改Android配置
- 在 `MainActivity.java` 中调整WebView设置
- 在 `themes.xml` 中自定义应用主题
- 在 `build.gradle.kts` 中修改应用配置

## 故障排除

### 常见问题

1. **编译错误**
   - 确保Android Studio版本兼容
   - 检查Gradle版本
   - 清理并重新构建项目

2. **WebView问题**
   - 确保设备支持WebView
   - 检查JavaScript是否启用
   - 查看Logcat中的错误信息

3. **触摸控制不响应**
   - 检查触摸事件监听器
   - 确保WebView正确配置
   - 测试不同设备的触摸灵敏度

### 调试模式
WebView调试已启用，可以通过Chrome DevTools进行调试：
1. 在Chrome中访问 `chrome://inspect`
2. 选择你的应用
3. 点击 "inspect" 开始调试

## 贡献

欢迎提交Issue和Pull Request来改进这个项目！

## 许可证

本项目采用MIT许可证 - 详见LICENSE文件

## 更新日志

### v1.0.0
- 初始版本发布
- 基础贪吃蛇游戏功能
- Android WebView集成
- 触摸控制支持
- 分数记录系统