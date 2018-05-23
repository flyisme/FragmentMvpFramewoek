# 自用MVP框架 支持单View多Presenter的绑定销毁。
## 基于开源项目 .[Fragmentation](https://github.com/YoKeyword/Fragmentation/blob/master/README_CN.md)修改，集成了滑动关闭fragment以及切换动画。
为"单Activity ＋ 多Fragment","多模块Activity + 多Fragment"架构而生，简化开发，轻松解决动画、嵌套、事务相关等问题。

网络请求使用RxJava2+retrofit2。

使用rxlifecycle处理Rxjava生命周期与Activity和Fragment生命周期协同问题。

dagger2依赖注入

# 如何使用

**1. 根下app的build.gradle中依赖：**

````gradle
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
````
**1. 项目下app的build.gradle中依赖：**


````gradle
dependencies {
	        dependencies {
	        implementation 'com.github.flyisme:FragmentMvpFramework:1.1.21'
			//Dagger2注解依赖
			annotationProcessor "com.google.dagger:dagger-compiler:2.7"
	}
````

**2. Activity `extends` MvpActivity（继承自RxAppCompatActivity）
````java
public class MainActivity extends MvpActivity {

    private long time_start = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findFragment(ContentFragment.class) == null) {
            loadRootFragment(R.id.content, ContentFragment.newInstance());  // 加载根Fragment
        }
		//更多使用参考[Fragmentation]支持类似Activity的启动方式eg：startForResult(ISupportFragment toFragment, int requestCode)...

    }
````

**3. Fragment `extends` MvpFragment（继承自RxFragment）
public class ContentFragment extends MvpFragment<ContentFragmentPresenter>
	//dagger2依赖注入
	@Inject
    ContentFragmentPresenter mPresenter;
	
    @Override
    public ContentFragmentPresenter getPresenter() {
        if (mPresenter == null) {
            ShApplication.get(_mActivity).getAppComponent().getMainComponent().inject(this);
            changeArgs();
        }
        return mPresenter;
    }
}

超类完成presenter的绑定与销毁
````