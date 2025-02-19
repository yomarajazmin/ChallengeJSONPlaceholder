# JSON Placeholder Challenge

To execute all the API test cases:
```
gradle clean test
```

*Test cases*:
- Obtain all posts
- Create a post
- Update a post
- Delete a post

The last two are not passing because they depend on the creation of a new post and due to the limitations of the testing environment this is not possible therefore they never reach the step that is in charge of updating/deleting the post.  
