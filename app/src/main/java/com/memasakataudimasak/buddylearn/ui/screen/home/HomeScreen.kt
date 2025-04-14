package com.memasakataudimasak.buddylearn.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.memasakataudimasak.buddylearn.R

@Composable
fun listCard(
    modifier: Modifier = Modifier,
    painter : Array<Int>,
    lessonGrades : Array<Int>,
    duration : Array<Int>,
    descriptions: Array<String>,
    titles : Array<String>,
    itemIndex: Int,
    onClick: (Int) -> Unit,
//    navController: NavController
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(370.dp)
            .padding(
                top = 20.dp,
                bottom = 20.dp
            )
//            .clickable {
//                navController.navigate(route= "learnPage/$itemIndex")
//            }
    ){
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ){
            Image(
                painter = painterResource(id = painter[itemIndex]),
                contentDescription = titles[itemIndex],
                modifier = Modifier
                    .width(140.dp)
                    .height(110.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp))
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ){
                    Text(text = titles[itemIndex],
                        modifier = Modifier.weight(1f),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(text = "Grade ${lessonGrades[itemIndex]}",
                        modifier = Modifier.weight(1f),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_thin)),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Box(modifier = Modifier.width(120.dp)){
                    Text(text = descriptions[itemIndex],
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_thin)),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 8.dp)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.timer),
                        contentDescription = "Time Needed",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${duration[itemIndex]} minutes",
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

@Composable
fun latestCard(
    modifier: Modifier = Modifier,
    painter : Array<Int>,
    lessonGrades : Array<Int>,
    latestTopic : Int,
    latestSubTopic : Int,
    duration : Array<Int>,
    descriptions: Array<String>,
    titles : Array<String>,
    itemIndex: Int,
    onClick: (Int) -> Unit,
//    navController: NavController
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(370.dp)
            .padding(
                top = 20.dp,
                bottom = 20.dp
            )
//            .clickable {
//                navController.navigate(route= "learnPage/$itemIndex")
//            }
    ){
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ){
            Image(
                painter = painterResource(id = painter[itemIndex]),
                contentDescription = titles[itemIndex],
                modifier = Modifier
                    .width(140.dp)
                    .height(110.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )
           Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
               horizontalArrangement = Arrangement.spacedBy(130.dp)
           )
           {
               Text(
                   text = titles[itemIndex],
                   modifier = Modifier.weight(1f),
                   fontSize = 12.sp,
                   fontFamily = FontFamily(Font(R.font.roboto_bold)),
                   maxLines = 2,
                   overflow = TextOverflow.Ellipsis
               )

               Text(
                   text = "Grade ${lessonGrades[itemIndex]}",
                   modifier = Modifier.weight(1f),
                   fontSize = 12.sp,
                   fontFamily = FontFamily(Font(R.font.roboto_thin)),
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
               )
           }
            Column(modifier = Modifier.fillMaxHeight()
                .width(250.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text("Topic ${latestTopic}",
                        fontSize = 22.dp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold))
                    )

                    Text("Subtopic ${latestSubTopic}",
                        fontSize = 18.dp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold))
                    )
                }
                Box(modifier = Modifier.width(230.dp)){
                    Text(text = descriptions[itemIndex],
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_thin)),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 8.dp)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.timer),
                        contentDescription = "Time Needed",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${duration[itemIndex]} minutes",
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 10.sp
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "Continue",
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 10.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_right_24),
                        contentDescription = "Time Needed",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onCardClicked: () -> Unit = {},
    imageID : Array<Int>,
    grades : Array<Int>,
    latestTopic : Int,
    latestSubTopic : Int,
    lessonDuration : Array<Int>,
    lessonDescriptions: Array<String>,
    lessonNames : Array<String>,
//    navController: NavController
) {
    var lastPressedIndex by remember { mutableStateOf<Int?>(null) }

    Column(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = (size.height - strokeWidth / 2) + 14
                    drawLine(
                        color = Color(red = 185, green = 185, blue = 185),
                        start = Offset(x = 0f, y = y),
                        end = Offset(x = size.width, y = y),
                        strokeWidth = strokeWidth
                    )
                }
        ) {
            Text(
                text = "Last Learned",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.monda))
            )
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.background,
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(370.dp)
                .padding(
                    top = 20.dp,
                    bottom = 20.dp
                )
//                .clickable {
//                    navController.navigate(route= "learnPage/$lastPressedIndex")
//                }
        ) {

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = (size.height - strokeWidth / 2) + 14
                    drawLine(
                        color = Color(red = 185, green = 185, blue = 185),
                        start = Offset(x = 0f, y = y),
                        end = Offset(x = size.width, y = y),
                        strokeWidth = strokeWidth
                    )
                }
        ) {
            Text(
                text = "Recommended",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.monda))
            )
        }

    LazyColumn(
        modifier = Modifier
        .fillMaxSize()
        .padding(
            top = 12.dp,
            start = 20.dp,
            end = 20.dp)
        .verticalScroll(rememberScrollState())
    ){
        val itemCount = imageID.size
        items(itemCount){
            listCard(
                modifier,
                painter = imageID,
                titles = lessonNames,
                lessonGrades = grades,
                descriptions = lessonDescriptions,
                duration = lessonDuration,
                itemIndex = it,
                onClick = { pressedIndex -> lastPressedIndex = pressedIndex },
//                navController = navController
            )
        }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    val imageID = arrayOf(
        R.drawable.p1,
        R.drawable.p2,
        R.drawable.p3,
        R.drawable.p4,
        R.drawable.p5,
        R.drawable.p6
    )
    val lessonGrades = arrayOf(
        3,
        3,
        9,
        10,
        11,
        12
    )
    val lessonDuration = arrayOf(
        3,
        30,
        29,
        100,
        22,
        34
    )

    val lessonNames = arrayOf(
        "Peperoni",
        "Vegan",
        "FourCheese",
        "Margaritta",
        "American",
        "Mexican"
    )

    val lessonDescriptions = arrayOf(
        "Tomato sos, cheese, oregano, peperoni",
        "Tomato sos, cheese, oregano, spinach, green paprika, rukola",
        "Tomato sos, oregano, mozzarella, goda, parmesan, cheddar",
        "Tomato sos, cheese, oregano, bazillion",
        "Tomato sos, cheese, oregano, green paprika, red beans",
        "Tomato sos, cheese, oregano, corn, jalapeno, chicken",
    )

    val latestTopic = 2
    val latestSubTopic = 4

    var selectedTheme by remember { mutableStateOf("light") }
//    selectedTheme = selectedTheme, onThemeChange = {newTheme -> selectedTheme = newTheme}
//    HomeScreen(
//        imageID = imageID,
//        lessonDescriptions = lessonDescriptions,
//        lessonDuration = lessonDuration,
//        lessonNames = lessonNames,
//        grades = lessonGrades,
//        latestTopic = latestTopic,
//        latestSubTopic = latestSubTopic,
//        navController = NavController
//    )
}