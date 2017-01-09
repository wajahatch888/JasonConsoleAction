# $console

## Usage

### 1. Basic
Stops further execution of the action call chain and displays all the current variable values.

#### Example
```json
{
  "type": "$network.request",
  "options": {
    "type": "$console.debug"
  }
}
```


### 2. Evaluation
Does all of the above, but also evaluate the expression passed in, and display it under "evaluated"

#### Options:
  - `eval`: A string to evaluate using the javascript engine.


#### Example
```json
{
  "type": "$network.request",
  "options": {
    "type": "$console.debug",
    "options": {
      "eval": "{{$jason.items}}"
    }
  }
}
```


## Full Example

### JSON

```
{
  "$jason": {
    "head": {
      "title": "$console.debug test",
      "actions": {
        "$load": {
          "type": "$set",
          "options": {
            "adjective": "sparkling",
            "noun": "water"
          },
          "success": {
            "type": "$cache.set",
            "options": {
              "username": "gliechtenstein"
            },
            "success": {
              "type": "$network.request",
              "options": {
                "url": "https://jasonbase.com/things/gbe"
              },
              "success": {
                "type": "$console.debug",
                "options": {
                  "eval": "I love {{$get.adjective}} {{$get.noun}}"
                }
              }
            }
          }
        }
      }
    }
  }
}
```

### Result

![console](https://raw.githubusercontent.com/gliechtenstein/images/master/console.png)
