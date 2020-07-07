package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TimePicker;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Struct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button btnSave;
    private Button btnShow;
    private EditText editText;
    private RadioButton radioButtonAzul;
    private RadioButton radioButtonRojo;
    private Spinner spinner;
    private SeekBar seekBar;
    private TextView txtPorcentaje;
    private QuickContactBadge qcbMail;
    private QuickContactBadge qcbPhone;
    private TextView txtRating;
    private RatingBar ratingBar;
    private Switch aSwitch;
    private RadioGroup radioGroup;
    private GridView gridview1;
    private WebView paginaWeb;
    private ProgressBar barraProgresoWeb;
    private SearchView searchView;
    private TimePicker timePicker;
    private TextView txtHours;
    private DatePicker datePicker;
    private TextView txtDate;
    private VideoView videoView;
    private ImageView imageViewGlide;
    private ViewStub  viewStub;
    private MapView mapView;
//    private AdView adView;


    private long dateMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.BLUE);
//        Así agregamos el Layout XML que tenemos creado, en este caso activity_main
        setContentView(R.layout.activity_main);
//        Asi es como agregamos el LinearLayout que acabamos de crear
//        setContentView(linearLayout);

        txtPorcentaje = (TextView)findViewById((R.id.txtPorcentaje));

//        <<<<<<<<<<<<<<<<<ListView>>>>>>>>>>>>>>>>>
        //Adaptadores
//        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this
//                ,R.layout.my_disign
//                ,R.id.textItem
//                ,Student.getStudents());
        final AdapterStudent adapterStudent = new AdapterStudent(this,R.layout.my_disign, Student.getStudents());
        ListView listView = (ListView)findViewById((R.id.listView));
        listView.setAdapter(adapterStudent);
        //Se ejecuta cuando seleccionas algun item de la lista. GridView utiliza el mismo setOnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = (Student) adapterStudent.getItem(i);
                txtPorcentaje.setText(student.getName());
            }
        });
        //Se ejecuta cuando dejas precionado en un item de la lista
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = (Student) adapterStudent.getItem(i);
                txtPorcentaje.setText(student.getName());
                txtPorcentaje.setTextColor(Color.RED);
                return false;
            }
        });

//        <<<<<<<<<<<<<<<<<GridView>>>>>>>>>>>>>>>>>
        GridView gridView = (GridView)findViewById((R.id.gridview1));
        gridView.setAdapter(adapterStudent);
        //Se ejecuta cuando seleccionas algun item de la lista. GridView utiliza el mismo setOnItemClickListener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = (Student) adapterStudent.getItem(i);
                txtPorcentaje.setText(student.getName());
            }
        });
        //Se ejecuta cuando dejas precionado en un item de la lista
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = (Student) adapterStudent.getItem(i);
                txtPorcentaje.setText(student.getName());
                txtPorcentaje.setTextColor(Color.RED);
                return false;
            }
        });

        btnSave = (Button)findViewById(R.id.btnSave);
//        btnSave.setBackgroundColor(Color.GREEN);
//        Para utilizar los colores que se encuentran en res/values/colors.xml
        btnSave.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorVerde));

        //Edicion de EdithText
        editText = (EditText)findViewById(R.id.EditorDeTexto);
        editText.setHint("Password");
        editText.setTextSize(10);
        editText.setBackgroundColor(Color.WHITE);
        Toast.makeText(getApplicationContext(), editText.getText(), Toast.LENGTH_LONG).show();


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
            ,LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(getApplicationContext());
        textView.setText("Muebles America!!! Donde pagas poco");
        params.leftMargin= 30;
//        Agregas los parametros para el TextView
//        textView.setLayoutParams(params);
        textView.setPadding(0,20,0,0);
        textView.setBackgroundColor(Color.RED);
        linearLayout.addView(textView,params);

//        Le asignamos la funcionalidad del boton creado en el XML al objeto que creamos "btnShow"
        btnShow = (Button)findViewById(R.id.btnShow);
//        Le damos funcionalidad de CLick al boton
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //Mandamos llamar la Actividad (Activity)(Ventana) MainActivity2
//                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                //Con putExtra() mandamos valores a la seiguiente actividad (Activity)
//                intent.putExtra("age",32);
//                intent.putExtra("welcome", "Bienvenido");
//                intent.putExtra("relaxed", true);
//                startActivity(intent);

//                Para mandar datos a otra aplicación realizamos lo siguiente
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,"Mi primer mensaje");
                intent.setType("text/plain"); //Si queremoes enviar una imagen colocamos "image/jpg"
//                //Para poder enviarle al usuario una ventana para que seleccione la aplicacion con la que desea abrir lo que le estoy enviando se hace lo siguiente
                startActivity(Intent.createChooser(intent, "Envia el texto a"));
            }
        });
        //Para realizar un permiso realizamos lo siguiente. si el permiso me devuelve 0 = tenemos el permiso -1 = no tenemos el permiso
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Permiso Aceptado", Toast.LENGTH_LONG).show();
        }
        else {
//          //Ejecutamos la funcion onRequestPermissionsResult de solicitud de permiso
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA}, 1);
            Toast.makeText(getApplicationContext(), "Permiso Denegado", Toast.LENGTH_LONG).show();
        }

//        <<<<<<<<<<<<<<<<<RadioButton>>>>>>>>>>>>>>>>>
        radioButtonAzul = (RadioButton)findViewById(R.id.radioButtonAzul);
        radioButtonRojo = (RadioButton)findViewById(R.id.radioButtonRojo);
        //Saber si ya esta seleccionado
        if(radioButtonRojo.isChecked())
            Toast.makeText(getApplicationContext(), "Rojo", Toast.LENGTH_LONG).show();
        //Mandar llamar la accion cuando seleccionas el radiobutton
        radioButtonAzul.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    Toast.makeText(getApplicationContext(), "Azul", Toast.LENGTH_LONG).show();
            }
        });

//        <<<<<<<<<<<<<<<<<RadioGroup>>>>>>>>>>>>>>>>>
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupColor);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)findViewById(i);
                txtPorcentaje.setText(radioButton.getText());
            }
        });
        //Saber cual RadioButton esta seleccionado
//        RadioButton radioButton = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());

//        <<<<<<<<<<<<<<<<<Spinner>>>>>>>>>>>>>>>>>
//        //Tenemos que utilizar otro layout en este caso "R.layout.item_adapter" para poder postrar las opciones
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this,R.layout.item_adapter,R.id.txtItem,Student.getStudents());
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

//        <<<<<<<<<<<<<<<<<SeekBar>>>>>>>>>>>>>>>>>
        seekBar = (SeekBar)findViewById(R.id.seekBar);

        //Nos va a crear 3 funciones
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            Esta funcion nos va indicando el progreso
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtPorcentaje.setText(i + "%");
            }
//            Esta funcion nos indica donde inicio
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                txtPorcentaje.setText("Inicio");
            }
//            esta funcion nos indica donde termino o sea cuando solto el circulo
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txtPorcentaje.setText("Termino");
            }
        });

//       <<<<<<<<<<<<<<<<<QuickContactBadge>>>>>>>>>>>>>>>>>
//        QuickContactBadge nos sirve para agregar los contactos al telefono
        qcbMail = (QuickContactBadge)findViewById(R.id.email);
        qcbPhone = (QuickContactBadge)findViewById(R.id.phone);
//      boolean lazyLookup = si esta en true muestra el e-mail
        qcbMail.assignContactFromEmail("al.torres@mavi.mx",true);
        //Realizamos validacion de versiones
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Bundle bundle = new Bundle();
            bundle.putString(ContactsContract.Intents.Insert.NAME,"Alan");
            qcbPhone.assignContactFromPhone("336968547854", true, bundle);
        } else {
            qcbPhone.assignContactFromPhone("336968547854", true);
        }

//        <<<<<<<<<<<<<<<<<RatingBar>>>>>>>>>>>>>>>>>
        txtRating = (TextView)findViewById(R.id.txtRating);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                txtRating.setText(v + " Estrellas");
            }
        });

//        <<<<<<<<<<<<<<<<<Switch>>>>>>>>>>>>>>>>>
        aSwitch = (Switch)findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    aSwitch.setText("Activado");
                else
                    aSwitch.setText("Desactivado");
            }
        });

        //        <<<<<<<<<<<<<<<<<WebView>>>>>>>>>>>>>>>>>
        paginaWeb = (WebView)findViewById(R.id.paginaWeb);
        //Agregamos la pagina que se va a visitar
        paginaWeb.loadUrl("https://www.google.com");
        //Podemos cargar codigo HTML de la siguiente manera
//        paginaWeb.loadData("<HTML><BODY>HOLA</BODY></HTML>","text/html; charset=utf-8",null);
        //Activamos JavaScript
        WebSettings webSettings = paginaWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //Zoom en el texto
        webSettings.setTextZoom(40);
        //Progreso de carga de la pagina web con un ProgressBar
        barraProgresoWeb = (ProgressBar)findViewById(R.id.barraProgresoWeb);
        paginaWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //No es necesario agregar la siguiente linea si se le pone como propiedad al ProgressBar indeterminate
                barraProgresoWeb.setProgress(newProgress);
                if(newProgress == 100)
                    barraProgresoWeb.setVisibility(View.GONE);
                super.onProgressChanged(view, newProgress);
            }
        });

        //        <<<<<<<<<<<<<<<<<SearchView>>>>>>>>>>>>>>>>>
        searchView = (SearchView)findViewById(R.id.SearchView1);
        CharSequence charSequence =  searchView.getQuery();
        CharSequence hint = searchView.getQueryHint();
        //SearchView no tiene que estar en version 7 eso lo podemos determinar en el import android.widget.SearchView;

        //Se crean dos funciones
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //Esta funcion se manda a llamar cuando el usuario preciona la tecla "Enter"
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtPorcentaje.setText("Buscar" + s);
                return false;
            }
            //esta funcion se manda a llamar mientras el usuario esta escribiendo en la caja de texto
            @Override
            public boolean onQueryTextChange(String s) {
                txtPorcentaje.setText(s);
                return false;
            }
        });

        //        <<<<<<<<<<<<<<<<<TimePicker>>>>>>>>>>>>>>>>>
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        //Este atributo nos sirve para determinar si el relog se va a manejar de 24 horas o de 12 horas am y pm
        timePicker.setIs24HourView(false);
        //Se asigna la hora solo en 24 horas
        //Realizamos validacion de API
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(14);
            timePicker.setMinute(12);
        } else {
            timePicker.setCurrentHour(14);
            timePicker.setCurrentMinute(12);
        }

        txtHours = (TextView)findViewById(R.id.txtHours);

        //Utilizamos s24HourView si me devuelve un true es por que estamos manejando el reloj a 24 horas

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                txtHours.setText(timePicker.getHour() + ":" + timePicker.getMinute());
            } else {
                txtHours.setText(timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute());
            }
        } catch (Exception e) {
            txtHours.setText(e.toString());
        }

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                    txtHours.setText(i + ":" + i1);
            }
        });

        //        <<<<<<<<<<<<<<<<<DatePicker>>>>>>>>>>>>>>>>>
        txtDate = (TextView)findViewById(R.id.txtDate);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        //El mes nos aparece del 0 al 11
        txtDate.setText(datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear());
        //podemos obtener el primer dia de la semana getFirstDayOfWeek() entre otras cosas mas

        //Realizamos una limitación de fechas
        //Se debe de insertar la fecha en milisegundo para ello se va a hacer la siguiente operacion
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
        try {
            Date date = simpleDateFormat.parse("2020/09/27");
            dateMillis = date.getTime();
        } catch(ParseException pe) {
            pe.printStackTrace();
        }
        datePicker.setMaxDate(dateMillis);

        //Asignamos fecha al calendario y generamos evento. Recuerda que el mes inicia Enero = 0
        datePicker.init(2020, 06, 01, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                txtDate.setText(i2 + "/" + i1 + "/" + i);
            }
        });


        //        <<<<<<<<<<<<<<<<<VideoView>>>>>>>>>>>>>>>>>
        videoView = (VideoView)findViewById(R.id.videoView);
        //Vamos a cargar el archivo guardado en la maquina en la carpeta res/raw
        String path = "android.resource://" + getPackageName() + "/" + R.raw.smallvideo;
        videoView.setVideoURI(Uri.parse(path));
        //Le agregamos controles al video (Play, Stop, etc...)
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        //Cargamos video desde la pagina web donde se encuentra
//        videoView.setVideoPath("http://www.demonuts.com/Demonuts/smallvideo.mp4");
        //Agregamos en que milisegundo queremos que inicie el video
        videoView.seekTo(2000);
        //Iniciamos el video en pausa
        videoView.pause();
        //Ejecutamos evento cuando se le da un click a la pantalla
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(videoView.isPlaying())
                    videoView.pause();
                else
                    videoView.start();
                return false;
            }
        });
        //Este evento se ejecuta cuando finaliza el video
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //Volvemos a ejecutar el video cuando finalice
                mediaPlayer.start();
            }
        });

        //        <<<<<<<<<<<<<<<<<Glide>>>>>>>>>>>>>>>>>

        imageViewGlide = (ImageView)findViewById(R.id.imageViewGlide);
        Glide.with(this).load("https://www.mueblesamerica.mx/CreditoEmpresario/img/logo-ma-blanco.png").into(imageViewGlide);
//       Se le pueden agregar imagenes por default o sea que esten cargadas en el dispositivo por si no hay internet
//        Glide.with(this).load("https://www.mueblesamerica.mx/CreditoEmpresario/img/logo-ma-blanco.png").placeholder(R.drawable.email).into(imageViewGlide);
        //Si nos aparece un error al cargar la imagen tambien nos puede cargar la imagen que tenemos por default
//        Glide.with(this).load("https://www.mueblesamerica.mx/CreditoEmpresario/img/logo-ma-blanco.png").placeholder(R.drawable.email).error(R.drawable.email).into(imageViewGlide);
        //Se le puede asignar dimenciones de la foto de mas baja calidad para que cargue mas rapido la imagen
//        Glide.with(this).load("https://www.mueblesamerica.mx/CreditoEmpresario/img/logo-ma-blanco.png").placeholder(R.drawable.email).override(10,10).error(R.drawable.email).into(imageViewGlide);

//        <<<<<<<<<<<<<<<<<ViewStub>>>>>>>>>>>>>>>>>
        viewStub = (ViewStub)findViewById(R.id.ViewSubTable);

//        <<<<<<<<<<<<<<<<<MapView>>>>>>>>>>>>>>>>>
        mapView = (MapView)findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
//                Guardamos la latitud y longitud
                LatLng guatemala = new LatLng(15.6356088,-89.8988087);
                //Le indicamos google que tipo de mapa nos muestre en este caso le indicamos que el satelital
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//              Le indicamos las cordenadas de donde queremos que nos muestre el mapa
                googleMap.addMarker(new MarkerOptions().position(guatemala).title("Guatemala"));
                //Enfocamos la camara a la ubicacion que queremos
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(guatemala));
                //realizamos zoom o lo alejamos el valor mas cerca es 2.0 y el mas lejano es 21.0
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(5.3f));
            }
        });

        //        <<<<<<<<<<<<<<<<<AdView>>>>>>>>>>>>>>>>>
//        adView = (AdView)findViewById(R.id.mapView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

//        test();
        //Toast.LENGTH_LONG: Duracion
        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_LONG).show();
    }

    //Actividad onClick del boton btnSave
    //Tenemos que recibir el atributo View
    //Tiene que ser public si no, no va a ser reconocido
    //Para poder manipular el boton tenemos que realizarlo dentro de esta función o mandarlo a llamar por el ID (findViewById)
    public void save(View view){
        Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_LONG).show();
        Button button = (Button)view;
        button.setEnabled(false);
//        button.setVisibility(View.GONE); //Cambia la visibilidad del boton. INVISIBLE, GONE: desaparece de la pantaya y del layout, VISIBLE
    }

    public void validar(View view) {
        View view1 = viewStub.inflate();
    }

//    //Funcion para solicitar permiso del uso de alguna parte del dispositivo como por ejemplo la Camara
//    //Para probar esta solicitud se tiene que hacer en una version mayor o igual al 6.0.0 API 23
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permiso Aceptado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permiso Denegado", Toast.LENGTH_LONG).show();
                    //Si necesitamos mucho el persmiso lo volvemos a solicitar
                    ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA}, 1);
                }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Se ejecutan procesos del MapView
        mapView.onStart();
        Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Se ejecutan procesos del MapView
        mapView.onResume();
        Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Se ejecutan procesos del MapView
        mapView.onPause();
        Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Se ejecutan procesos del MapView
        mapView.onStop();
        Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Se ejecutan procesos del MapView
        mapView.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart", Toast.LENGTH_LONG).show();
    }

    //Esta funcion se ejecuta cuando Android se esta quedando sin memoria
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //Se ejecutan procesos del MapView
        mapView.onLowMemory();
    }

    //onSaveInstanceState y onRestoreInstanceState nos sirven para guardar y restaurar los datos cuando giramos el celular y se gira la pantalla
    //onSaveInstanceState nos sirve para guardar los datos
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name","Alan");
        //Se ejecutan procesos del MapView
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(), "Nombre: " + savedInstanceState.getString("name","Valor default"), Toast.LENGTH_LONG).show();
    }

    //    Los mensajes que se pueden enviar en el LogCat
    public void test() {
        Log.e("test","Error");
        Log.w("test","Advertencia");
        Log.i("test","informar");
        Log.d("test","Depueracion");
        Log.v("test", "Detalle");

    }
}