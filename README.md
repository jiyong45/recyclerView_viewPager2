# recyclerView_viewPager2

Pages must fill the whole ViewPager2  (use match_parent)에러 이유


viewPager의 OnChildAttachStateChangeListener 에서 
if (layoutParams.width != LayoutParams.MATCH_PARENT || layoutParams.height != LayoutParams.MATCH_PARENT) 이구문을 통해 
match_parent 가 아닐경우 예외를 발생 시키도록 진행하기때문임...
                        

<pre><code>
private RecyclerView.OnChildAttachStateChangeListener enforceChildFillListener() {
        return new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                RecyclerView.LayoutParams layoutParams =
                        (RecyclerView.LayoutParams) view.getLayoutParams();
                if (layoutParams.width != LayoutParams.MATCH_PARENT
                        || layoutParams.height != LayoutParams.MATCH_PARENT) {
                    throw new IllegalStateException(
                            "Pages must fill the whole ViewPager2 (use match_parent)");
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                // nothing
            }
        };
    }
</code></pre>


그리고 viewpager 보다 viewpager2 를 사용하면 좋은이유는 viewpager2와 recyclerview 의 형태가 비슷해서 

전환이 용이하기때문에 전환이 용이 즉 유지보수하기에 좋기때문에 대응성이 좋다 


그리고 RecyclerView 안에 ViewPager 를 넣게되면 

ViewPager는 PagerAdapter 기반 구성으로써  스크롤 할때마다 destroyItem 이 호출되어 버벅거릴 수 있다.

그러기에 ViewPager2 는 RecyclerView를 기반으로 만들어진 컴포넌트라서 

RecyclerView 에 RecyclerView를 넣은것 처럼 PagerSnapHelper 를 별도로 커스텀하여 개발할 필요가 없어진다고 한다.

//pagerSnapHelper 는 또 뭔지... 확인해보기 
