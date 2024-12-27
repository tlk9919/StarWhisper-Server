import com.baomidou.mybatisplus.generator.FastAutoGenerator;

/**
 * @author admin
 * @classnam CodeGenerator
 * @time 22:12
 * @date 2024/12/12
 */
public class CodeGenerator {
    public static void main(String[] args) {
// 使用 FastAutoGenerator 快速配置代码生成器
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/starwhisper", "root", "root")
                .globalConfig(builder -> {
                    builder.author("admin_tlk") // 设置作者
                            .outputDir("D:\\Code\\JavaWebFrameworkProject\\StarWhisper-Server\\src\\main\\java"); // 输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.starwhisperserver") // 设置父包名
                            .entity("model") // 设置实体类包名
                            .mapper("dao") // 设置 Mapper 接口包名
                            .service("service") // 设置 Service 接口包名
                            .serviceImpl("service.impl"); // 设置 Service 实现类包名
//                            .xml("mappers"); // 设置 Mapper XML 文件包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user","verification_code") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .controllerBuilder()
                            .enableRestStyle(); // 启用 REST 风格
                })
                .execute(); // 执行生成
    }
}
