# ViewHolderFactory

ViewHolderFactoryAnnotation: [ ![Download](https://api.bintray.com/packages/jefryjacky/AndroidLibrary/ViewHolderFactoryAnnotation/images/download.svg) ](https://bintray.com/jefryjacky/AndroidLibrary/ViewHolderFactoryAnnotation/_latestVersion)    
ViewHolderFactoryProcessor: [ ![Download](https://api.bintray.com/packages/jefryjacky/AndroidLibrary/ViewHolderFactoryProcessor/images/download.svg) ](https://bintray.com/jefryjacky/AndroidLibrary/ViewHolderFactoryProcessor/_latestVersion)   

# ViewHolderFactory

**Gradle**
```  
dependencies {  
    ...  
    implementation 'com.jefryjacky:viewholderfactoryannotation:1.0.1'
    kapt 'com.jefryjacky:viewholderfactoryprocessor:1.0.1'
}  
```  

# Usage
1. Add ViewHolderFactoryAnnotation with layoutname about your view holder class
```kotlin
@ViewHolderAnnotation("item_list")
class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(value: String){
        itemView.apply {
            tv_label.text = value
        }
    }
}
``` 

2. Build for generate the factory code

3. When create viewholder in your adapter you just call yourviewholderclassnameFactory.create for create view holder
```kotlin
class Adapter : ListAdapter<String, ItemViewHolder>(AdapterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolderFactory.create(parent)
    }

``` 
