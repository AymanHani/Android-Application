package com.example.jawwalprojectfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;

    ViewPager2 onboardingViewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);
        setOnboardingItems();
        onboardingViewPager2 = findViewById(R.id.onboardingViewPager2);
        onboardingViewPager2.setAdapter(onboardingAdapter);

        setLayoutOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });
        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager2.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager2.setCurrentItem(onboardingViewPager2.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });
    }

    private void setOnboardingItems() {
        List<OnboardingItem> onboardingItems = new ArrayList<>();
        OnboardingItem OnlineShopping = new OnboardingItem();
        OnlineShopping.setTitle("Online Shopping");
        OnlineShopping.setDescription("More and more third world science and technology educated people are heading for more prosperous countries seeking higher wages and better working conditions.");
        OnlineShopping.setImage(R.drawable.onboardingscreen1);

        OnboardingItem AddToCart = new OnboardingItem();
        AddToCart.setTitle("Add to Card");
        AddToCart.setDescription("More and more third world science and technology educated people are heading for more prosperous countries seeking higher wages and better working conditions.");
        AddToCart.setImage(R.drawable.onboardingscreen2);

        OnboardingItem OrderDilivery = new OnboardingItem();
        OrderDilivery.setTitle("Order Dilivered");
        OrderDilivery.setDescription("More and more third world science and technology educated people are heading for more prosperous countries seeking higher wages and better working conditions.");
        OrderDilivery.setImage(R.drawable.onboardingscreen3);

        onboardingItems.add(OnlineShopping);
        onboardingItems.add(AddToCart);
        onboardingItems.add(OrderDilivery);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }
    private void setLayoutOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(), R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }
    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView)layoutOnboardingIndicators.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if (index == onboardingAdapter.getItemCount() - 1) {

            buttonOnboardingAction.setText("Start");
        }else{
            buttonOnboardingAction.setText("Next");
        }
    }
}