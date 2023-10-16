@file:OptIn(ExperimentalLayoutApi::class)

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import components.ChatComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ChatContent(component: ChatComponent) {
    val model by component.model.subscribeAsState()
    val coroutineScope = rememberCoroutineScope()
    val lazyState = rememberLazyListState()
    val animatedColor = animateColorAsState(
        if (model.mText.isNotBlank()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
            alpha = .7f
        )
    )

    if(model.costil) {
        println("asd")
        coroutineScope.launch {
            lazyState.animateScrollToItem(0)
        }
        component.costilF()
    }

    val keyboardHeight = KeyboardHeight()
    val list = remember { mutableStateListOf<Int>() }
    Scaffold {
        Column(Modifier.fillMaxSize()) {
            Row(
                Modifier.fillMaxWidth().padding(it).padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        component.onBackClicked()
                    },
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Icon(Icons.Default.ArrowBack, null)
                }
                Text(model.title, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                IconButton(
                    onClick = {
                        component.clearMessages()
                    },
                    modifier = Modifier.padding(end = 10.dp)
                ) {
                    Icon(Icons.Default.Delete, null)
                }
            }
            Spacer(Modifier.size(5.dp))
            Divider(Modifier.fillMaxWidth().height(1.dp))
            BoxWithConstraints(Modifier.offset(y = -keyboardHeight)) {
                val height = this.maxHeight.value.dp
                val width = this.maxWidth.value.dp
                Column(verticalArrangement = Arrangement.Bottom) {
                    LazyColumn(
                        Modifier.weight(0.1f).padding(horizontal = 5.dp)
                            .padding(top = keyboardHeight),
                        verticalArrangement = Arrangement.Bottom,
                        reverseLayout = true,
                        state = lazyState
                    ) {
                        items(model.messages.reversed(), key = { it.index }) {
                            val state = remember {
                                if (list.contains(it.index)) mutableStateOf(true) else
                                    mutableStateOf(false)
                            }
                            if (!list.contains(it.index)) {

                                coroutineScope.launch {

                                    delay(1)
                                    state.value = true
                                    list.add(it.index)
                                    coroutineScope.launch {
                                        lazyState.animateScrollToItem(0)
                                    }
                                }
                            }

                            AnimatedVisibility(
                                visible = state.value,
                                enter = expandVertically(expandFrom = Alignment.Top)
                            ) {
                                MessageUnit(
                                    it.text,
                                    it.hourMinutes,
                                    it.isBot,
                                    orderWords(it.words),
                                    width
                                ) { word ->
                                    component.onWordClicked(word)
                                    coroutineScope.launch {
                                        lazyState.animateScrollToItem(0)
                                    }
                                }
                            }
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
                    Spacer(Modifier.size(5.dp))
                    Divider(Modifier.fillMaxWidth().height(1.dp))
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





                            Box(
                                Modifier.fillMaxWidth().height(55.dp).padding(end = 10.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                IconButton(
                                    enabled = model.mText.isNotBlank(),
                                    onClick = {
                                        component.sendMessage()
                                        coroutineScope.launch {
                                            lazyState.animateScrollToItem(0)
                                        }
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

        }


//        Box(
//            Modifier.fillMaxWidth().padding(it),
//            contentAlignment = Alignment.CenterStart
//            ) {
//            IconButton(
//                onClick = {
//                    component.onBackClicked()
//                },
//                modifier = Modifier.padding(10.dp)
//            ) {
//                Icon(Icons.Default.ArrowBack, null)
//            }
//            Text(model.title, Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
//        }

    }

}

fun orderWords(words: List<String>): List<String> {
    val bigWords = words.filter { it.length > 6 }
    val smallWords = words.filter { it.length <= 6 }

    val result = mutableListOf<String>()

    val minSize = minOf(bigWords.size, smallWords.size)
    for (i in 0 until minSize) {
        result.add(bigWords[i])
        result.add(smallWords[i])
    }

    result.addAll(bigWords.subList(minSize, bigWords.size))
    result.addAll(smallWords.subList(minSize, smallWords.size))

    return result
}

@ExperimentalFoundationApi
@Composable
fun MessageUnit(
    text: String, time: String, isMine: Boolean, words: List<String>, width: Dp,
    onClick: (String) -> Unit
) {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = if (isMine || width > 600.dp) Alignment.CenterStart else Alignment.CenterEnd
    ) {
        Column(Modifier.widthIn(max = width * 0.8f).padding(bottom = 5.dp)) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isMine) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                BoxWithConstraints(Modifier.padding(5.dp)) {
                    Text(text, Modifier.padding(bottom = 5.dp, end = 30.dp, start = 5.dp))
                    Text(time, fontSize = 10.sp, modifier = Modifier.align(Alignment.BottomEnd))
                }
            }
            if (words.isNotEmpty()) {
                Row() {
                    val wordsNum = when (words.size) {
                        1 -> 1
                        else -> 2
                    }
                    for (it in words.subList(0, wordsNum)) {
                        OutlinedButton(
                            onClick = {
                                onClick(it)
                            },
                            modifier = Modifier.padding(start = 5.dp, top = 3.dp),
                            contentPadding = PaddingValues(vertical = 0.dp, horizontal = 8.dp)
                        ) {
                            Text(it)
                        }
                    }
                }
                if (words.size > 2) {
                    Row() {
                        val wordsNum = when (words.size) {
                            3 -> 3
                            else -> 4
                        }
                        for (it in words.subList(2, wordsNum)) {
                            OutlinedButton(
                                onClick = {
                                    GlobalScope.launch {
                                        onClick(it)
                                    }
                                },
                                modifier = Modifier.padding(start = 5.dp),
                                contentPadding = PaddingValues(
                                    vertical = 0.dp,
                                    horizontal = 8.dp
                                )
                            ) {
                                Text(it)
                            }
                        }
                    }
                }
            }
        }
    }


}