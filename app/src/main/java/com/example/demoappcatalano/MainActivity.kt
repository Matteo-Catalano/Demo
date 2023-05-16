package com.example.demoappcatalano

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demoappcatalano.api.getPokemonNames
import com.example.demoappcatalano.ui.theme.DemoAppCatalanoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoAppCatalanoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PokemonCardList()
                }
            }
        }
    }
}

@Composable
fun PokemonCardList() {
    val list = remember { mutableStateListOf<String>() }

    SideEffect {
        getPokemonNames { name, _ ->
            if (name != null) {
                list.addAll(name)
            }
        }
    }
    MaterialTheme {
        LazyColumn {
            items(list) { name ->
                PersonalizedCard(name = name)
            }
        }
    }
}


@Composable
fun PersonalizedCard(name: String) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .width(400.dp)
            .background(Color.Gray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp)
        ) {
            Text(text = name)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(horizontal = 2.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.icon2),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(text = "NUMBER")
            Button(onClick = {}) {
                Text(text = "GO TO DETAIL")
            }
        }

    }
}

