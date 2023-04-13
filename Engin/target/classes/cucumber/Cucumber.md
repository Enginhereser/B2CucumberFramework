## Gherkin ve Cucumber?
Cucumber, Behavior-driven development (BDD) odaklı bir test aracıdır. Gherkin dilini kullanır. 
Hikaye anlatır gibi test yazabilirsiniz, bu sayede yazılım bilgisi 
olmayan kişiler bile kolaylıkla test oluşturabilir.

## Cucumber Nedir?
Cucumber, Behaviour-Driven Development ( Davranış Odaklı Geliştirme ) destekli bir test aracıdır.

Test edilecek durumlar, Gherkin isimli bir gramer yapısını kullanarak varsayılan olarak İngilizce olmakla beraber, mevcut çevirileri kullanılarak Türkçe dahil birçok diğer dilde de basit cümleler kurarak yazılabilmektedir. Bu sayede herhangi bir teknik bilgisi olmayan kişilerin dahi yazılım testi oluşturabilmesine imkan vermektedir. Kurulumu, testlerin yazımı ve kullanımı gayet kolay olduğu için test yazma işini yorucu ve sıkıcı bir durum olmaktan kurtarak eğlenceli ve kolay bir hale getirmektedir.

Cucumber aracı Ruby dili ile geliştirilmiştir ve Ruby, Python, Java, JavaScript, C++ , Lua, Kotlin, .NET ve php başta olmak üzere birçok dilde test yazmayı desteklemektedir.

## Gherkin Nedir
Gherkin, Cucumber ile test durumları yazarken kullanılan gramer yapısının genel adıdır. Gayet basit ve anlaması kolay olan bu yapıda kurulan cümleler günlük konuşma dilindeki cümlelere oldukça yakın olduğu için test durumlarını yazarken adeta yazılımla konuşuyormuş hissiyatı oluşturur.

Gherkin dili belirli anahtar kelimelerden faydalanır.

Bir testi ve senaryoları isimlendirmek için kullanılan anahtar kelimeler aşağıdaki gibidir:

**Feature :** Testi Yapılacak Özelliğin Adı

**Scenario :** Uygulanacak Senaryo Adı

**Scenario Outline :** Aynı senaryoyu birden fazla durum için çalıştırmak gerektiğinde taslak senaryo oluşturmak için kullanılır.

Tanımlanan senaryoda uygulanacak işlem adımlarını tanımlamak için de aşağıdaki anahtar kelimeler kullanılır. Bu işlemlerin her birine “Step” yani adım adı verilir.


**Given:** Sistemin başlangıç durumunu tanımlar. Sistemin tanımlanmış bir duruma geçmesini sağlar.

**When:** Bir olay yada aksiyonu tanımlamak için kullanılır. Yapılmak istenen bir eylem bu anahtar kelimeden sonra yazılır.

**Then:** Beklenen bir sonucu yada durumu tanımlamak için kullanılır. Beklenen durumla gerçekleşen durumu karşılaştırdığımız ifade bu anahtar kelimeden sonra yazılır.

**And, But:** Yukarıda bahsedilen Given ,When ve Then ifadelerinden birden fazla ardarda yazılmak istendiğinde bu anahtar kelimeler kullanılır.


## İyi BDD Örneği
İyi örneklerin temel özelliklerinden bazıları şunlardır:

**Tek bir davranışa veya özelliğe odaklanın:** Her senaryo, uygulamanın tek bir davranışına veya özelliğine odaklanır, bu da testin anlaşılmasını ve sürdürülmesini kolaylaştırır.

**Net adımlar ve doğrulama noktaları:** Senaryolar, uygulamanın davranışının etkin bir şekilde test edildiğinden emin olmak için net adımlar ve doğrulama noktaları içerir.

**Açıklayıcı ve açık adların kullanımı:** Özellik dosyaları, senaryolar ve adım tanımları, testin anlaşılmasını ve sürdürülmesini kolaylaştıran açıklayıcı ve net adlara sahiptir.

```gherkin
  Feature: Shopping cart
  As a shopper
  I want to be able to add and remove items from my shopping cart
  So that I can manage the items I want to purchase

  Scenario: Add an item to the shopping cart
    Given I am on the product page for a shirt
    When I click the "add to cart" button
    And I select the size and quantity of the shirt
    And I click the "add to cart" button again
    Then I should see a notification that says "Shirt added to cart"
    And the shopping cart icon should show the correct number of items

  Scenario: Remove an item from the shopping cart
    Given I am on the shopping cart page
    And there is at least one item in the cart
    When I click the "remove" button for an item
    Then I should see a notification that says "Item removed from cart"
    And the shopping cart icon should show the updated number of items
    And the item should no longer be listed in the shopping cart

  Scenario: Empty the shopping cart
    Given I am on the shopping cart page
    And there are multiple items in the cart
    When I click the "empty cart" button
    Then I should see a notification that says "Shopping cart emptied"
    And the shopping cart icon should show 0 items
    And the shopping cart should be empty
```

