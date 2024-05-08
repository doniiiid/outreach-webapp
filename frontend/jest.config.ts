const config: any = {
  roots: ['./src'],
  moduleNameMapper: {
    '\\.(css|svg|png)$': '<rootDir>/config/test/styleMock.js',
  },
  verbose: true,
  preset: 'ts-jest',
  testEnvironment: 'jsdom',
  transform: {
    "^.+\\.tsx?$": "babel-jest"
  },
  coverageReporters: ['html', 'text'],
};

export default config;