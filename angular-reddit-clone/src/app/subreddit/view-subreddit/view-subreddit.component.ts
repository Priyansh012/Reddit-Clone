import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostModel } from 'src/app/shared/post-model';
import { PostService } from 'src/app/shared/post.service';
import { SubredditService } from '../subreddit.service';

@Component({
  selector: 'app-view-subreddit',
  templateUrl: './view-subreddit.component.html',
  styleUrls: ['./view-subreddit.component.css']
})
export class ViewSubredditComponent implements OnInit {

  posts: Array<PostModel> = [];
  id: number;
  constructor(private postService: PostService, private activatedRoute: ActivatedRoute,private subredditService: SubredditService) {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.postService.getAllPostsBySubreddit(this.id).subscribe(data => {
    this.posts = data;
    });
  }

  ngOnInit(): void {
  }

}

