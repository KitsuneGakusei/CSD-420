# Module 7 Programming Assignment — Styled Circles (JavaFX + CSS)

**Author:** Crystal Long  
**Date:** 2025-09-16

This program displays **four circles** using JavaFX and an external CSS file:
- Class `.plaincircle` → white fill, black stroke.
- IDs `#redcircle` and `#greencircle` → red and green circles.

It includes a lightweight **test** (`CircleViewTest.java`) that verifies the scene graph and CSS are applied.

---

## Compile & Run (Windows PowerShell)

Set this once per session to your JavaFX **lib** folder (your confirmed install path):

```powershell
$env:JAVA_FX_LIB = 'C:\Users\K1tt3\openjfx-24.0.2_windows-x64_bin-sdk\javafx-sdk-24.0.2\lib'
```

**Compile:**
```powershell
javac --module-path "$env:JAVA_FX_LIB" --add-modules javafx.controls,javafx.graphics,javafx.swing `
  CircleView.java CircleViewTest.java
```

**Run tests:**
```powershell
java --module-path "$env:JAVA_FX_LIB" --add-modules javafx.controls,javafx.graphics,javafx.swing `
  --enable-native-access=javafx.graphics `
  CircleViewTest
```

**Run the app:**
```powershell
java --module-path "$env:JAVA_FX_LIB" --add-modules javafx.controls,javafx.graphics,javafx.swing `
  --enable-native-access=javafx.graphics `
  CircleView
```

> The `--enable-native-access` flag silences a JavaFX 24 warning. It’s not required for functionality.

---

## Notes
- Keep `mystyle.css` in the **same folder** as the `.java` files (or adjust `getResource` to an absolute resource path if you package the classes).
- No external code copied; implementation based on the course assignment prompt for Chapter 31 CSS usage.

