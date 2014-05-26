package cn.org.xmind.test;

/**
 *
 * @author LuoWenqiang
 */
public class TestPackage {

    public static void main(String[] args) {
        ClassLoader loader = TestPackage.class.getClassLoader();
        System.out.println(loader);
        Package[] packages = Package.getPackages();
        for (Package pk : packages) {
            System.out.println("name : " + pk.getName());
            System.out.println("title : " + pk.getSpecificationTitle());
            System.out.println("vendor : " + pk.getSpecificationVendor());
            System.out.println("version : " + pk.getSpecificationVersion());
            System.out.println("=========================================");
        }
    }
}
