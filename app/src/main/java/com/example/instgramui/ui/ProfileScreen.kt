package com.example.instgramui.ui

import android.icu.number.Scale
import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instgramui.R

private const val TAG = "ProfileScreen"
@Preview
@Composable
fun ProfileScreen() {
    Column(
        Modifier.fillMaxSize()
    ) {
        TopBar(
            userName = "Ahmed rashed"
        )
        Spacer(modifier = Modifier.height(4.dp))
        ProfileSection()
        DrawTab(){
            Log.d(TAG, "ProfileScreen: $it")
        }
    }
}

@Composable
fun TopBar(
    userName: String, modifier: Modifier = Modifier, space: Dp = 15.dp
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(space))
        Text(
            text = userName,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(space))
        Icon(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "Bell",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(space))
        Icon(
            painter = painterResource(id = R.drawable.ic_dotmenu),
            contentDescription = "Menu",
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
) {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {

            RoundedImage(
                painter = painterResource(id = R.drawable.profile_image),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            StateSection(modifier = Modifier.weight(7f))
        }
        Description(
            displayedName = "Software engineer",
            description = "some dummy text",
            url = "www.youtube.com",
            followedBy = listOf("Ahmed", "Said", "Hassan"),
            otherCount = "18"
        )

    }
}

@Composable
fun Description(
    displayedName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    otherCount: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = displayedName,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black
        )
        Text(
            text = description, fontSize = 15.sp, color = Color.Black
        )
        Text(
            text = url, fontSize = 15.sp, color = Color.Blue
        )
        Text(text = buildAnnotatedString {
            val boldStyle =
                SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            append("followed by : ")
            followedBy.forEachIndexed { index, s ->
                pushStyle(boldStyle)
                append(s)
                pop()
                if (index < followedBy.size - 1) append(",")
                else {
                    append(" and")
                    pushStyle(boldStyle)
                    append(" $otherCount others")
                }
            }
        }, fontSize = 15.sp)
    }
}

@Composable
fun StateSection(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ProfileState(numberText = "601", text = "Posts")
        ProfileState(numberText = "99.5K", text = "Followers")
        ProfileState(numberText = "73", text = "Following")
    }
}

@Composable
fun ProfileState(
    numberText: String, text: String, modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp
        )
        Text(
            text = text, color = Color.Black, fontSize = 15.sp
        )
    }
}

@Composable
fun RoundedImage(
    painter: Painter, modifier: Modifier
) {
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                1.dp, Color.LightGray, CircleShape
            )
            .padding(3.dp)

            .clip(CircleShape)
    )
}

@Composable
fun DrawTab(
    modifier: Modifier = Modifier,
    selectedIndex: (index: Int) -> Unit
) {
    var mutableSelectedIndex by remember {
        mutableStateOf(0)
    }
    TabRow(
        selectedTabIndex = mutableSelectedIndex,
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = Color.Black
    ) {
        for (i in 0 until 4){
            Tab(
                selected = mutableSelectedIndex == i,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color(0xFF777777),
                onClick = {
                    mutableSelectedIndex = i
                    selectedIndex(i);
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bell),
                    contentDescription = "",
                    tint = if (mutableSelectedIndex == i) Color.Black
                    else Color(0xFF777777),
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@Composable
fun InsertTab(
    index: Int,
    currentIndex: Int,
    image: Painter,
    description: String ="",
    selectedIndex: (index: Int) -> Unit
) {


}

