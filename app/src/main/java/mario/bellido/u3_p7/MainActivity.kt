package mario.bellido.u3_p7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.google.gson.Gson
import kotlinx.coroutines.launch
import mario.bellido.u3_p7.ui.theme.BlueMain
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {TopBar()}
            )
            {
                Tabs_Principal()
            }

            val gson = Gson()
        }
    }
}

fun loadData(inFile : String) : String {

    var tContents = ""

    try {
        val stream = assets.open(inFile)
    }
    catch (e : IOException)
    {

    }

    return tContents

}

@Composable
@Preview
fun TopBar()
{
    TopAppBar(
        title = {
                Text(
                    text = "Alan Smith",
                    maxLines = 2,
                    modifier = Modifier
                        .height(100.dp)
                        .wrapContentHeight(Alignment.Bottom)

                )
        },
        modifier = Modifier.height(150.dp),
        backgroundColor = Color(0xFF02b6e3),
        contentColor = Color.White,
        elevation = 40.dp,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back", tint = Color(0xFF128099))
            }
        },
        actions = {
            Column() {
                Row()
                {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Notifications, contentDescription = "dangerous", tint = Color(0xFF128099))
                    }
                }
            }
        }
    )
}

@Composable
@Preview
fun prev()
{
    PInfo()
    PCheckins()
    PPhotos()
}

@Composable
fun PInfo()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
    ) {}
}

@Composable
fun PCheckins()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
    ) {}
}

@Composable
fun PPhotos()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
    ) {}
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs_Principal()
{
    val tabs = listOf(
        Tabs_item.item_info,
        Tabs_item.item_checkin,
        Tabs_item.item_photos
    )

    val pagerState = rememberPagerState()

    Column() {
        Tabs(tabs, pagerState)
        Tabs_content (tabs, pagerState)
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs:List<Tabs_item>, pagerState: PagerState)
{
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = {
            tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState,
                    tabPositions),
                    color = Color(0xFFe25094)
                )
        },
        backgroundColor = BlueMain
    ) {
        tabs.forEachIndexed {index, tabsItem ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = { scope.launch {
                    pagerState.animateScrollToPage(index)
                } },
                icon = {},
                text = { Text(tabsItem.title) },
                unselectedContentColor = Color(0xFF128099),
                selectedContentColor = Color.White

            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs_content(tabs: List<Tabs_item>, pagerState: PagerState)
{
    HorizontalPager(
        state = pagerState,
        count = tabs.size,
        verticalAlignment = Alignment.Top, //add this
        modifier = Modifier
            .wrapContentHeight() //and this
            .fillMaxSize()
    ) {
        page ->
        tabs[page]

        if (tabs[page].title == "CHECK-INS")
        {
            PersonCard()
            PersonCard()

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            )
            {
                PersonCard()
                PersonCard()
            }

        }
    }
}

@Composable
fun PersonCard()
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .padding(0.dp)
            )

            Column(Modifier.padding(15.dp)) {
                Text(
                    text = "James Richardson",
                    fontSize = 18.sp,
                    color = Color.Gray
                    )
                Row() {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Text(
                        text = "San Francisco, CA",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

