package com.example.appfirebase

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appfirebase.ui.theme.AppFireBaseTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppFireBaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    appCliente(innerPadding)
                }
            }
        }
    }
}

@Composable
fun appCliente(innerPadding: PaddingValues) {
    var nome by remember { mutableStateOf("") }
    var EndeTXT by remember { mutableStateOf("") }
    var BaiTXT by remember { mutableStateOf("") }
    var CEPTXT by remember { mutableStateOf("") }
    var CidTXT by remember { mutableStateOf("") }
    var EstadoTXT by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row {
            Text(
                text = "App Aula",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }

        OutlinedTextField(
            value = nome,
            onValueChange = {
                nome = it
            },
            label = { Text("Nome:") }
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            Arrangement.Center
        ) {
            OutlinedTextField(
                value = EndeTXT,
                onValueChange = {
                    EndeTXT = it
                },
                label = { Text("Endereço") }
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            Arrangement.Center
        ) {
            OutlinedTextField(
                value = BaiTXT,
                onValueChange = {
                    BaiTXT = it
                },
                label = { Text("Bairro:") }
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            Arrangement.Center
        ) {
            OutlinedTextField(
                value = CEPTXT,
                onValueChange = {
                    CEPTXT = it
                },
                label = { Text("CEP:") }
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            Arrangement.Center
        ) {
            OutlinedTextField(
                value = CidTXT,
                onValueChange = {
                    CidTXT = it
                },
                label = { Text("Cidade:") }
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            Arrangement.Center
        ) {
            OutlinedTextField(
                value = EstadoTXT,
                onValueChange = {
                    EstadoTXT = it
                },
                label = { Text("Estado:") }
            )
        }

        Button(onClick = {
            val db = Firebase.firestore

            val cliente = hashMapOf(
                "Nome" to nome,
                "Endereco" to EndeTXT,
                "Bairro" to BaiTXT,
                "CEP" to CEPTXT,
                "Cidade" to CidTXT,
                "Estado" to EstadoTXT
            )
            db.collection("clientes")
                .add(cliente)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

        }) {
            Text("Cadastrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppFireBaseTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            appCliente(innerPadding)
        }
    }
}