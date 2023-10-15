@file:OptIn(ExperimentalLayoutApi::class)

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.overscroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import components.ChatComponent

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ChatContent(component: ChatComponent) {
    val model by component.model.subscribeAsState()

    val keyboardHeight = KeyboardHeight()
    Scaffold {

        BoxWithConstraints(Modifier.offset(y = -keyboardHeight)) {
            val height = this.maxHeight.value.dp
            val width = this.maxWidth.value.dp
            Column(verticalArrangement = Arrangement.Bottom) {
                LazyColumn(
                    Modifier.weight(0.1f).padding(horizontal = 5.dp).padding(top = keyboardHeight),
                    verticalArrangement = Arrangement.Bottom,
                    reverseLayout = true
                ) {
                    items(model.messages.reversed(), key = { it.index }) {
                        Message(it.text, it.hourMinutes, it.isBot, width)
                        Spacer(Modifier.height(1.dp))

                        if (it == model.messages.first() || it.day != model.messages[it.index - 1].day
                        ) {
                            Box(
                                Modifier.fillMaxWidth().padding(vertical = 15.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(it.day, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            }

                        }

//                    LaunchedEffect(Unit) {
//                        coroutineScope.launch {
//                            bringIntoViewRequester.bringIntoView()
//                        }
//                    }

                    }
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = height * 0.3f)
                ) {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomStart) {
                        OutlinedTextField(
                            value = model.mText,
                            modifier = Modifier
                                .width(width - 45.dp),
                            onValueChange = {
                                component.onMessageTextChange(it)
                            },
                            placeholder = {
                                Text(
                                    "Введите сообщение...",
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent,
                                errorBorderColor = Color.Transparent,
                            )
                        )

                        val animatedColor = animateColorAsState(
                            if (model.mText.isNotBlank()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                                alpha = .7f
                            )
                        )
                        Box(
                            Modifier.fillMaxWidth().height(55.dp).padding(end = 10.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            IconButton(
                                enabled = model.mText.isNotBlank(),
                                onClick = {
                                    component.sendMessage()
                                }) {
                                Icon(
                                    Icons.Rounded.Send,
                                    null,
                                    modifier = Modifier.size(30.dp),
                                    tint = animatedColor.value
                                )
                            }
                        }
                    }
                }
            }

        }
        IconButton(
            onClick = {
                component.onBackClicked()
            },
            modifier = Modifier.padding(it).padding(10.dp)
        ) {
            Icon(Icons.Default.ArrowBack, null)
        }

    }

}


@ExperimentalFoundationApi
@Composable
fun Message(text: String, time: String, isMine: Boolean, width: Dp) {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = if (isMine || width > 600.dp) Alignment.CenterStart else Alignment.CenterEnd
    ) {
        Card(
            Modifier.widthIn(max = width * 0.8f),
            colors = CardDefaults.cardColors(
                containerColor = if (isMine) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Box(Modifier.padding(5.dp)) {
                Text(text, Modifier.padding(bottom = 5.dp, end = 30.dp, start = 5.dp))
                Text(time, fontSize = 10.sp, modifier = Modifier.align(Alignment.BottomEnd))
            }
        }
    }
}