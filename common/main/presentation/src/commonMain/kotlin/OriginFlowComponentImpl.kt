import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import components.OriginFlowComponent
import components.OriginFlowComponent.Child.BookInfoChild
import components.OriginFlowComponent.Child.ChatChild
import components.OriginFlowComponent.Child.MainChild
import components.inFlow.BookInfoComponent
import di.Inject
import inFlow.BookInfoComponentImpl
import inFlow.MainComponentImpl

class OriginFlowComponentImpl(
    componentContext: ComponentContext
) : OriginFlowComponent, ComponentContext by componentContext {
    //private val settingsRepository: SettingsRepository = Inject.instance()

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        initialConfiguration = Config.Main,
        handleBackButton = true,
        childFactory = ::child
    )

    override val childStack: Value<ChildStack<*, OriginFlowComponent.Child>> = stack

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): OriginFlowComponent.Child =
        when (config) {
            is Config.Main -> MainChild(
                MainComponentImpl(componentContext) {
                    navigation.push(
                        Config.BookInfo(
                            it.dbName,
                            it.title,
                            it.author,
                            it.date,
                            it.point,
                            it.description,
                            it.titlePrev
                        )
                    )
                }
            )

            is Config.BookInfo -> BookInfoChild(
                BookInfoComponentImpl(
                    componentContext,
                    BookInfoComponent.Model(
                        config.dbName,
                        config.title,
                        config.author,
                        config.date,
                        config.point,
                        config.description,
                        config.titlePrev
                    ),
                    {
                        navigation.push(Config.Chat(config.titlePrev, config.dbName))
                    }
                ) {
                    navigation.pop()
                }
            )

            is Config.Chat -> ChatChild(
                ChatComponentImpl(
                    componentContext
                ) {
                    navigation.pop()
                }
            )

        }


    @Parcelize
    private sealed interface Config : Parcelable {
        data object Main : Config

        data class BookInfo(
            val dbName: String,
            val title: String,
            val author: String,
            val date: String,
            val point: String,
            val description: String,
            val titlePrev: String
        ) : Config

        data class Chat(
            val titlePrev: String,
            val dbName: String
        ) : Config
    }
}