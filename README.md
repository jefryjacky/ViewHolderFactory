# ViewHolderFactory

ViewHolderFactoryAnnotation: [ ![Download](https://api.bintray.com/packages/jefryjacky/AndroidLibrary/ViewHolderFactoryAnnotation/images/download.svg) ](https://bintray.com/jefryjacky/AndroidLibrary/ViewHolderFactoryAnnotation/_latestVersion)    
ViewHolderFactoryProcessor: [ ![Download](https://api.bintray.com/packages/jefryjacky/AndroidLibrary/ViewHolderFactoryProcessor/images/download.svg) ](https://bintray.com/jefryjacky/AndroidLibrary/ViewHolderFactoryProcessor/_latestVersion)   

Auto generate factory for instance RecycleViewAdapter Viewholder

**Gradle**
```  
dependencies {  
    ...  
    implementation 'com.jefryjacky:viewholderfactoryannotation:1.0.1'
    kapt 'com.jefryjacky:viewholderfactoryprocessor:1.0.1'
}  
```  

# Usage
1. Add ViewHolderFactoryAnnotation with layoutname above your view holder class
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

### License
<pre>
Copyright 2020 Jefry Jacky

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
