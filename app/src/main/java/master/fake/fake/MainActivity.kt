package master.fake.fake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import master.fake.fake.databinding.ActivityMainBinding

const val TAG = "_tag_debug"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


/*
        private lateinit var navController: NavController
        navController = Navigation.findNavController(this, R.id.hostFragment)
        bottom_nav.setupWithNavController(navController)
*/


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bottomNav.setupWithNavController(navController)


    }
}