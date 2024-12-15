package StructuralPatterns.Adapter;

public class AdapterExample {
    public static void main(String[] args) {
        JapanProduct japanFan = new JapanFan();

        KoreaProduct adapter = new VoltAdapter(japanFan);
        adapter.use220V();

        KoreaProduct koreaAircon = new KoreaAircon();
        koreaAircon.use220V();
    }
}

interface JapanProduct {
    void use110V();
}

class JapanFan implements JapanProduct{

    @Override
    public void use110V() {
        System.out.println("일본 선풍기 가동중");
    }
}

interface KoreaProduct {
    void use220V();
}

class KoreaAircon implements KoreaProduct{

    @Override
    public void use220V() {
        System.out.println("한국 에어컨 가동중");
    }
}

class VoltAdapter implements KoreaProduct{
    private JapanProduct japanProduct;

    public VoltAdapter(JapanProduct japanProduct) {
        this.japanProduct = japanProduct;
    }

    @Override
    public void use220V() {
        System.out.println("한국 어댑터 : 110V의 제품을 220V에 맞게 변환합니다.");
        japanProduct.use110V();
    }
}