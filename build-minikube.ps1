# Переключаемся на Docker minikube (PowerShell)
& minikube -p minikube docker-env --shell powershell | Invoke-Expression

# Массив с именами сервисов
$services = @(
  "telegram-service",
  "user-service",
  "weather-service",
  "weather-analyzer"
)

$projectRoot = "C:\Users\d.zubets\IdeaProjects\PDP"

Set-Location $projectRoot

foreach ($service in $services) {
    Write-Host "Building image for $service..."
    docker build -t "$service:latest" --build-arg SERVICE_NAME=$service .
}

Write-Host "All images built and ready to be used in Minikube."
