package Matdol.SmartGazalBee.DeviceComparison.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService{
    private static final Map<String, String> translationMap = new HashMap<>();

    static {
        translationMap.put("아이", "i");
        translationMap.put("갤", "gal");
        translationMap.put("겔", "gal");
        translationMap.put("아이폰", "iphone");
        translationMap.put("갤럭", "galax");
        translationMap.put("겔럭", "galax");
        translationMap.put("갤럭시", "galaxy");
        translationMap.put("겔럭시", "galaxy");
    }

    @Override
    public String translateToEnglish(String text) {
        return translationMap.getOrDefault(text,text);
    }
}
