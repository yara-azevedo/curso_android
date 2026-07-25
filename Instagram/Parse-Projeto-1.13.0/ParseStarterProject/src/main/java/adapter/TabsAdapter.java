package adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.parse.starter.R;

import java.util.HashMap;

import fragments.HomeFragment;
import fragments.UsuariosFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private String[] abas = new String[] {"HOME", "USUÁRIOS"};
    private int[] icones = new int[] {R.drawable.home2, R.drawable.user2};
    private int tamanhoIcone;
    private HashMap<Integer, Fragment> fragmentHashMap = new HashMap<>();
    public TabsAdapter(@NonNull FragmentManager fm, Context c) {
        super(fm);
        this.context = c;
        double escala = this.context.getResources().getDisplayMetrics().density;
        tamanhoIcone = (int) (36*escala);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new HomeFragment();
                fragmentHashMap.put(position,fragment);
                break;
            case 1:
                fragment = new UsuariosFragment();
               // fragmentHashMap.put(position,fragment);
                break;

        }
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        fragmentHashMap.remove(fragmentHashMap);
    }

    public Fragment getFragment(Integer indice){
        return fragmentHashMap.get(indice);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable =ContextCompat.getDrawable(context, icones[position]);
        drawable.setBounds(0,0,tamanhoIcone,tamanhoIcone);

        ImageSpan imageSpan = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;

    }

    @Override
    public int getCount() {
        return icones.length;
    }
}
