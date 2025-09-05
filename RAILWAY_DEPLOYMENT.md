# Railway.app Deployment Guide

This guide explains how to deploy the Spring Boot application to Railway.app and configure it to avoid memory issues.

## Prerequisites

1. A Railway.app account
2. The Railway CLI installed (optional but recommended)
3. Git installed on your machine

## Step 1: Prepare your application for Railway

The application is already configured with the following optimizations:

- Memory-optimized Docker configuration
- Production-specific application properties
- Environment variable support
- Connection pool optimization
- JVM memory settings for containerized environments

## Step 2: Create a new Railway project

1. Log in to your Railway.app account
2. Click "New Project" 
3. Select "Deploy from GitHub repo"
4. Connect to your GitHub repository and select the appropriate repo

## Step 3: Add a MySQL database

1. In your Railway project, click "New"
2. Select "Database" and then "MySQL"
3. Railway will automatically provision a MySQL database

## Step 4: Configure environment variables

In your Railway project settings, add the following environment variables:

```
MYSQL_DATABASE=dgsme
MYSQL_USERNAME=<railway-provided-username>
MYSQL_PASSWORD=<railway-provided-password>
MYSQL_HOST=<railway-provided-host>
MYSQL_PORT=<railway-provided-port>
SPRING_PROFILES_ACTIVE=prod
JWT_SECRET=<your-secure-jwt-secret>
```

The values for MySQL connection details can be found in the Railway MySQL service variables.

## Step 5: Adjust memory allocation

1. Go to your service settings in Railway
2. Set the RAM allocation to at least 512MB (recommended: 1GB)
3. This will prevent out-of-memory errors

## Step 6: Deploy your application

Railway will automatically detect the Dockerfile and build your application. The Dockerfile is configured to:

1. Build the application with Maven
2. Run with optimized JVM settings 
3. Use a slim JRE image to minimize container size
4. Set appropriate memory constraints

## Troubleshooting

If you still encounter memory issues:

1. Check Railway logs for specific error messages
2. Try increasing the RAM allocation in Railway settings
3. Adjust the `JAVA_OPTS` environment variable to further optimize memory usage:

```
JAVA_OPTS=-XX:+UseContainerSupport -XX:MaxRAMPercentage=70.0 -XX:MinRAMPercentage=40.0 -Xss512k -XX:+UseG1GC
```

## Monitoring

1. Enable Railway metrics to monitor your application's resource usage
2. Check for memory leaks or excessive resource consumption in your logs

## Further Optimizations

- Consider using Railway's autoscaling feature for handling variable loads
- Set up proper database indexing to improve query performance
- Use Railway's CDN for serving static assets 