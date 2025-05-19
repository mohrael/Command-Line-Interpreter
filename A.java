 @BeforeEach
    void setUp() {
        cl = new CLI();
        // Set the current directory for tests (this can be adjusted as needed)
        testDirectory = System.getProperty("user.dir") + "/testDir"; 
        cl.setCurrentDirectory(testDirectory);  // Assuming there's a method to set currentDirectory

        // Create the test directory to ensure it exists
        File dir = new File(testDirectory);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }