// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Troye Sivan', 'Taylor Swift', 'JJ Lin', 'Stefanie Sun', 'Jay Zhou', 'Sodagreen', 'Mayday'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

// Change style of top container on scroll
window.onscroll = function() {myFunction()};
function myFunction() {
  if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
    document.getElementById("top").classList.add("w3-card-4", "w3-animate-opacity");
    document.getElementById("intro").classList.add("w3-show-inline-block");
  } else {
    document.getElementById("intro").classList.remove("w3-show-inline-block");
    document.getElementById("top").classList.remove("w3-card-4", "w3-animate-opacity");
  }
}
function randomizeImage() {
  var name = new Array('JayChou', 'JJLin', 'Sodagreen', 'Mayday', 'StefanieSun', 'Taylor', 'Troye');
  const imageIndex = Math.floor(Math.random() * 3);
  const imageName = Math.floor(Math.random() * 7);
  const imgUrl = 'images/' + name[imageName] + '_' + imageIndex + '.jpg';

  const imgElement = document.createElement('img');
  imgElement.src = imgUrl;

  const imageContainer = document.getElementById('random-image-container');
  // Remove the previous image.
  imageContainer.innerHTML = '';
  imageContainer.appendChild(imgElement);
}

