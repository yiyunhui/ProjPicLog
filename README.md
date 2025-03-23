# 学生信息管理系统

这是一个Android应用程序，用于管理学生信息、记录和拍照功能。

## 主要功能

1. 学生信息管理
   - 学生基本信息查询
   - Excel格式学生信息导入
   - 显示学生姓名、学号、项目名称等信息

2. 拍照记录功能
   - 根据学生信息自动生成照片名称
   - 拍照并保存为JPG格式
   - 支持语音备注（MP4格式）

3. 历史记录查询
   - 按时间顺序显示学生照片记录
   - 支持查看历史照片和语音备注

## 技术栈

- Android Studio
- Kotlin
- Room Database
- CameraX
- MediaRecorder
- Apache POI (Excel导入)

## 开发环境要求

### 必需工具
- Android Studio Hedgehog | 2023.1.1 或更高版本
- JDK 17 或更高版本
- Android SDK 33 或更高版本
- Gradle 8.0 或更高版本

### 系统要求
- Windows 10/11, macOS 10.14 或更高版本, Linux
- 最小内存要求：8GB RAM
- 推荐内存：16GB RAM
- 硬盘空间：至少10GB可用空间

## 项目结构

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/studentmanager/
│   │   │   ├── data/
│   │   │   │   ├── dao/          # 数据访问对象
│   │   │   │   ├── model/        # 数据模型
│   │   │   │   └── util/         # 工具类
│   │   │   ├── ui/
│   │   │   │   ├── screens/      # 界面组件
│   │   │   │   ├── theme/        # 主题相关
│   │   │   │   └── viewmodel/    # 视图模型
│   │   │   └── utils/            # 通用工具类
│   │   └── res/                  # 资源文件
│   └── test/                     # 测试文件
└── build.gradle                  # 项目构建配置
```

## 安装说明

1. 克隆项目到本地
```bash
git clone [项目地址]
```

2. 使用Android Studio打开项目

3. 等待Gradle同步完成

4. 运行项目
   - 连接Android设备或启动模拟器
   - 点击"运行"按钮或使用快捷键Shift+F10

## 使用说明

### 学生信息导入
1. 准备Excel文件，确保包含以下列：
   - 学号
   - 姓名
   - 项目名称
   - 班级
   - 院系

2. 点击"导入"按钮，选择Excel文件
3. 等待导入完成，查看导入结果

### 学生信息查询
1. 在查询界面输入学号和姓名
2. 点击"查询"按钮
3. 查看查询结果

## 注意事项

- 首次运行需要授予相机和存储权限
- Excel文件格式必须符合要求
- 建议定期备份数据库

## 贡献指南

1. Fork 项目
2. 创建特性分支
3. 提交更改
4. 推送到分支
5. 创建Pull Request

## 许可证

本项目采用 MIT 许可证 - 详见 LICENSE 文件