package inFlow

import DbBookNames
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import components.inFlow.BookInfoComponent
import components.inFlow.MainComponent
import getOjegov


@Composable
fun MainContent(
    component: MainComponent
) {
    val model by component.model.subscribeAsState()
    val ojegovP = getOjegov()
    val listOfBooks = listOf(
        BookInfoComponent.Model(
            dbName = DbBookNames.OjegovBook.name,
            title = "Толковый словарь русского языка",
            author = "С. И. Ожегов и Н. Ю. Шведова",
            date = "1949 г.",
            point = "Используется для изучения значений слов",
            description = "Один из наиболее известных " +
                    "и авторитетных словарей русского языка, " +
                    "созданный советским лексикографом в середине 20-го века. " +
                    "Этот словарь охватывает разнообразные слова и выражения русского языка, " +
                    "снабженные определениями и примерами использования.",
            titlePrev = "Толковый словарь Ожегова"
        )
    )
    Scaffold(
        Modifier.fillMaxSize()
    ) {
        BoxWithConstraints(
            Modifier.fillMaxSize()
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed((this@BoxWithConstraints.maxWidth / 150).value.toInt()),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp + it.calculateTopPadding(),
                    bottom = 20.dp
                )
            ) {
                item(span = { GridItemSpan(this.maxLineSpan) }) {
                    Box(
                        Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Чатарь", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    }
                }
                items(items = listOfBooks) {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(modifier = Modifier.clip(RoundedCornerShape(12.dp)).clickable(onClick = {component.onBookClicked(
                            it
                        )}).height(((this@BoxWithConstraints.maxWidth) / (this@BoxWithConstraints.maxWidth / 150)).dp + 50.dp)) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Image(
                                    if (it.dbName == DbBookNames.OjegovBook.name) ojegovP else ojegovP,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                        Spacer(Modifier.height(2.dp))
                        Text(it.titlePrev, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                    }

                }
            }
        }
    }
}