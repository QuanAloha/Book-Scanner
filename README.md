# Book Scanner Java

A Java/Gradle desktop application that turns sequences of camera images into a clean, ordered PDF book.  
The app will ingest images (including RAW via external converters), sort them using metadata (e.g., timestamps), automatically detect and crop page regions, deskew, apply readability enhancements, and export a single PDF.

## Goals

- Fast, keyboard-driven workflow inspired by Narrative Select for culling and confirming pages.
- Accept images from any source (phone, DSLR, flatbed) with flexible folder-based import.
- Use metadata (timestamps, camera info) to automatically order pages.
- Detect page boundaries, crop, and deskew automatically.
- Apply simple, robust image cleanup (grayscale, contrast, sharpening) optimized for text.
- Export the final sequence as a single multi-page PDF.
- (Later) Add OCR to produce searchable PDFs.

## High-level architecture

The application is built as a processing pipeline:

1. Import & sort  
   - Recursively read images from a folder.
   - Extract EXIF/metadata to sort by capture time, with manual overrides.

2. Preprocessing  
   - Normalize all inputs to a common working format (e.g., TIFF or high-quality JPEG).
   - (Planned) Support RAW files by calling an external converter or native library.

3. Page detection & deskew  
   - Automatically find the page region within each image.
   - Crop to the page and correct rotation/skew.

4. Image enhancement  
   - Convert to grayscale or lightly enhance contrast/sharpness for text clarity.
   - Keep operations predictable and batch-friendly.

5. PDF generation (and OCR later)  
   - Assemble all processed pages into a single PDF in order.
   - (Planned) Integrate OCR to embed a searchable text layer.

## Tech stack

- Language: Java (modern releases, managed with Gradle)
- Build tool: Gradle (application project)
- IDE/editor: Visual Studio Code
- Image and metadata libraries:
  - Java image I/O / additional imaging libraries (TBD)
  - Metadata extraction library for EXIF/XMP (TBD)
  - PDF generation library (e.g., Apache PDFBox, TBD)
- Platform: Desktop (initially targeting Windows/macOS, Java cross-platform)

## Current status

Early planning phase:

- Repository initialized with Gradle Java application skeleton.
- Requirements and architecture drafted in this README.
- Next step: choose image/metadata/PDF libraries and sketch the core pipeline interfaces.

## Getting started (development)

Prerequisites:

- Java (JDK 17+ recommended)
- Gradle or use the Gradle wrapper from this repo
- Git
- Visual Studio Code with Java and Gradle extensions

To build and run (once the project is wired up):

```
git clone https://github.com/<your-username>/book-scanner-java.git
cd book-scanner-java
./gradlew build
./gradlew run
```

(Replace the clone URL with your actual repository URL.)

## Roadmap

- v0.1: 
  - Folder import, metadata-based sort, manual page progression UI.
  - Export ordered images directly to a PDF (no auto-crop yet).

- v0.2:
  - Automatic page detection and deskew.
  - Basic image cleanup for text.

- v0.3:
  - RAW support via external converter or native library.
  - OCR and searchable PDFs.

## License

TBD. (For open-source, consider MIT, Apache 2.0, or GPL depending on dependencies.)