export default [
    {
      context: [
        '/api',
        '/services',
        '/management',
        '/v3/api-docs',
        '/h2-console',
        '/auth',
        '/health',
      ],
      target: 'http://localhost:8080',
      secure: false,
    },
  ];