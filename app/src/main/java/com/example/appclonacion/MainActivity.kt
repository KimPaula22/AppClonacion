package com.example.appclonacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appclonacion.ui.theme.AppClonacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppClonacionTheme {
                var selectedScreen by remember { mutableStateOf("Inicio") } // Controla la pantalla seleccionada

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar(onRefresh = {
                            // Acción de restablecer la pantalla a "Inicio"
                            selectedScreen = "Inicio"
                        })
                    },
                    bottomBar = {
                        BottomNavigationBar(
                            selectedScreen = selectedScreen,
                            onScreenSelected = { screen -> selectedScreen = screen }
                        )
                    }
                ) { innerPadding ->  // Este es el bloque donde se pasa innerPadding
                    // Mostrar la pantalla correspondiente según `selectedScreen`
                    when (selectedScreen) {
                        "Inicio" -> HomeScreen(modifier = Modifier.padding(innerPadding))
                        "Explorar" -> ExploreScreen(modifier = Modifier.padding(innerPadding))
                        "Perfil" -> ProfileScreen(modifier = Modifier.padding(innerPadding))
                        "Shorts" -> ShortsScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onRefresh: () -> Unit) {
    TopAppBar(
        title = {
            IconButton(onClick = onRefresh) {
                Icon(
                    painter = painterResource(id = R.drawable.icono_youtube1), // Cambia a tu logo
                    contentDescription = "YouTube Logo",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Unspecified // Para no tintar el logo
                )
            }
        },

    )
}
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }
    var isSearchVisible by remember { mutableStateOf(false) } // Agregar estado para mostrar/ocultar la barra de búsqueda

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Si isSearchVisible es verdadero, muestra la barra de búsqueda
        if (isSearchVisible) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Buscar", fontSize = 16.sp) },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
        }

        IconButton(
            onClick = {
                // Alternar la visibilidad de la barra de búsqueda al hacer clic en la lupa
                isSearchVisible = !isSearchVisible
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = "Buscar",
                modifier = Modifier.size(30.dp),
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedScreen: String,
    onScreenSelected: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_home),
                    contentDescription = "Inicio",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Unspecified
                )
            },
            label = { Text("Inicio") },
            selected = selectedScreen == "Inicio",
            onClick = { onScreenSelected("Inicio") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_suscripciones),
                    contentDescription = "Suscripciones",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Red
                )
            },
            label = { Text("Suscripciones") },
            selected = selectedScreen == "Suscripciones",
            onClick = { onScreenSelected("Suscripciones") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_perfil),
                    contentDescription = "Perfil",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Red
                )
            },
            label = { Text("Perfil") },
            selected = selectedScreen == "Perfil",
            onClick = { onScreenSelected("Perfil") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_shorts),
                    contentDescription = "Shorts",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Unspecified
                )
            },
            label = { Text("Shorts") },
            selected = selectedScreen == "Shorts",
            onClick = { onScreenSelected("Shorts") }
        )
    }
}
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        // Logo y Barra de búsqueda
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.boton_menu), // Logo de YouTube
                contentDescription = "Menu Busqueda",
                modifier = Modifier.size(48.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.weight(1f)) // Espacio flexible
            IconButton(onClick = { /* Acción futura */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_search),
                    contentDescription = "Buscar",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Simular un feed de videos
        LazyColumn {
            items(10) { index -> // Muestra 10 elementos ficticios
                VideoItem(
                    title = "Video $index",
                    channel = "Canal $index",
                    views = "${index * 1000} vistas"
                )
            }
        }
    }
}

@Composable
fun VideoItem(title: String, channel: String, views: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Miniatura del video
        Icon(
            painter = painterResource(id = R.drawable.icon_placeholder), // Placeholder para la miniatura
            contentDescription = "Miniatura del video",
            modifier = Modifier
                .size(120.dp)
                .padding(end = 8.dp),
            tint = Color.Gray
        )

        // Información del video
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 16.sp, maxLines = 2)
            Text(text = channel, fontSize = 14.sp, color = Color.Gray)
            Text(text = views, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun ExploreScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(5) { index ->
            ExploreItem(
                channelName = "Canal Suscrito $index",
                lastVideo = "Último video del Canal $index"
            )
        }
    }
}

@Composable
fun ExploreItem(channelName: String, lastVideo: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del canal
        Text(
            text = channelName,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Último video
        Text(
            text = lastVideo,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp)
        )

        // Espaciado entre elementos
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun SubscriptionsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Encabezado
        Text(
            text = "Tus suscripciones a ->",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de usuarios con videos
        LazyColumn {
            items(5) { index ->
                // Cada usuario suscrito
                SubscriptionItem(
                    userName = "Usuario ${index + 1}",
                    videos = listOf(
                        "Video ${index * 2 + 1} del Usuario ${index + 1}",
                        "Video ${index * 2 + 2} del Usuario ${index + 1}"
                    )
                )
            }
        }
    }
}
@Composable
fun SubscriptionItem(userName: String, videos: List<String>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Nombre del usuario
        Text(
            text = userName,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Lista de videos del usuario
        videos.forEach { video ->
            Text(
                text = video,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
            )
        }

        // Espaciado entre usuarios
        Spacer(modifier = Modifier.height(16.dp))
    }
}
@Composable
fun ShortsScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(5) { index -> // Simula 5 shorts
            ShortItem(videoNumber = index)
        }
    }
}

@Composable
fun ShortItem(videoNumber: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp) // Altura similar a un video vertical
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Simula un video corto con un rectángulo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(Color.Gray) // El color representa el "video"
        ) {
            // Texto que aparece encima del video
            Text(
                text = "Short $videoNumber",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Descripción del video corto
        Text(
            text = "Descripción corta del Short $videoNumber",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Sección del nombre
        Text(
            text = "Mi Canal: Nombre del Usuario",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Información adicional del canal
        Text(
            text = "Más información sobre este canal",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Botón de "Gestionar Videos"
        androidx.compose.material3.Button(
            onClick = { /* Sin acción por ahora */ },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Gestionar Videos")
        }

        // Sección de "Mis Videos"
        Text(
            text = "Mis Videos",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Lista de videos del canal
        LazyColumn {
            items(5) { index ->
                VideoItem(
                    title = "Video del canal $index",
                    channel = "Nombre del Usuario",
                    views = "${(index + 1) * 1000} vistas"
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppClonacionTheme {
        Greeting("Android")
    }
}