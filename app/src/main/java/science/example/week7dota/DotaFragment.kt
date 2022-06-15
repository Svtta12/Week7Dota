package science.example.week7dota

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import coil.load
import coil.size.Scale
import science.example.week7dota.ItemFragment.Companion.BUNDLE
import science.example.week7dota.ItemFragment.Companion.KEY
import science.example.week7dota.data.Repository.Companion.PHOTO_URL
import science.example.week7dota.databinding.FragmentDotaBinding
import science.example.week7dota.model.HeroDota


class DotaFragment : Fragment() {

    private var _binding: FragmentDotaBinding? = null
    private val binding: FragmentDotaBinding get() = _binding!!
    private lateinit var fmanager: FragmentManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDotaBinding.inflate(inflater, container, false)

        fmanager = requireActivity().supportFragmentManager
        fmanager.setFragmentResultListener(KEY, viewLifecycleOwner) { _, bundle ->
            val hero = bundle.get(BUNDLE) as HeroDota
            initVies(hero)
        }
        return binding.root
    }

    private fun initVies(hero: HeroDota) {
        binding.apply {
            textName.text = hero.name
            textNickname.text = hero.localizedName
            textAttackType.text = hero.attackType
            textPrimaryAttr.text = hero.primaryAttr
            textBaseStr.text = hero.baseStr
            textBaseAge.text = hero.baseAgi
            textBaseInt.text = hero.baseInt
            imageIcon.load(PHOTO_URL + hero.icon)
            imageSuperheroesDetail.load(PHOTO_URL + hero.img) {
                crossfade(true)
                scale(Scale.FILL)
            }
        }
    }
}