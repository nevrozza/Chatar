package inFlow

import DbBookNames
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.ChatBubbleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import components.inFlow.BookInfoComponent
import getOjegov
import javax.swing.Box

@Composable
fun BookInfoContent(
    component: BookInfoComponent
) {
    val model by component.model.subscribeAsState()

    val ojegovP = getOjegov()
    Scaffold() {
        IconButton(
            onClick = {
                component.onBackClicked()
            },
            modifier = Modifier.padding(it).padding(10.dp)
        ) {
            Icon(Icons.Default.ArrowBack, null)
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.fillMaxHeight(0.05f))
            Text(
                model.title,
                modifier = Modifier.padding(horizontal = 15.dp),
                fontWeight = FontWeight.Black,
                fontSize = 27.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.size(2.dp))
            Text(model.date)
            Spacer(Modifier.size(20.dp))
            Box(Modifier.fillMaxHeight(0.5f).clip(RoundedCornerShape(12.dp))) {
                Image(
                    if (model.dbName == DbBookNames.OjegovBook.name) ojegovP else ojegovP,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
            }
            Spacer(Modifier.size(10.dp))
            Text(model.author, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.size(5.dp))
            Text(model.point)
            Spacer(Modifier.size(10.dp))
            Text(
                model.description,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 10.dp),
                textAlign = TextAlign.Center
            )
            BoxWithConstraints(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = {
                              component.onChatClicked()
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = maxWidth * 0.2f)
                ) {
                    Text("Початиться")
                    Spacer(Modifier.width(5.dp))
                    Icon(Icons.Rounded.Chat, null)
                }
            }
        }
    }
}
